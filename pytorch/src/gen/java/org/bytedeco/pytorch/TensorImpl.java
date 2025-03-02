// Targeted by JavaCPP version 1.5.6: DO NOT EDIT THIS FILE

package org.bytedeco.pytorch;

import org.bytedeco.pytorch.Allocator;
import org.bytedeco.pytorch.Function;
import org.bytedeco.pytorch.Module;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;
import static org.bytedeco.openblas.global.openblas_nolapack.*;
import static org.bytedeco.openblas.global.openblas.*;

import static org.bytedeco.pytorch.global.torch.*;

// #endif

/**
 * The low-level representation of a tensor, which contains a pointer
 * to a storage (which contains the actual data) and metadata (e.g., sizes and
 * strides) describing this particular view of the data as a tensor.
 *
 * Some basic characteristics about our in-memory representation of
 * tensors:
 *
 *  - It contains a pointer to a storage struct (Storage/StorageImpl)
 *    which contains the pointer to the actual data and records the
 *    data type and device of the view.  This allows multiple tensors
 *    to alias the same underlying data, which allows to efficiently
 *    implement differing *views* on a tensor.
 *
 *  - The tensor struct itself records view-specific metadata about
 *    the tensor, e.g., sizes, strides and offset into storage.
 *    Each view of a storage can have a different size or offset.
 *
 *  - This class is intrusively refcounted.  It is refcounted so that
 *    we can support prompt deallocation of large tensors; it is
 *    intrusively refcounted so that we can still perform reference
 *    counted operations on raw pointers, which is often more convenient
 *    when passing tensors across language boundaries.
 *
 *  - For backwards-compatibility reasons, a tensor may be in an
 *    uninitialized state.  A tensor may be uninitialized in the following
 *    two ways:
 *
 *      - A tensor may be DTYPE UNINITIALIZED.  A tensor of this
 *        form has an uninitialized dtype.  This situation most
 *        frequently arises when a user writes Tensor x(CPU).  The dtype and
 *        is subsequently initialized when mutable_data<T>() is
 *        invoked for the first time.
 *
 *      - A tensor may be STORAGE UNINITIALIZED.  A tensor of this form
 *        has non-zero size, but has a storage with a null data pointer.
 *        This situation most frequently arises when a user calls
 *        Resize() or FreeMemory().  This is because Caffe2 historically
 *        does lazy allocation: allocation of data doesn't occur until
 *        mutable_data<T>() is invoked.  A tensor with zero size is
 *        always storage initialized, because no allocation is necessary
 *        in this case.
 *
 *    All combinations of these two uninitialized states are possible.
 *    Consider the following transcript in idiomatic Caffe2 API:
 *
 *      Tensor x(CPU); // x is storage-initialized, dtype-UNINITIALIZED
 *      x.Resize(4); // x is storage-UNINITIALIZED, dtype-UNINITIALIZED
 *      x.mutable_data<float>(); // x is storage-initialized, dtype-initialized
 *      x.FreeMemory(); // x is storage-UNINITIALIZED, dtype-initialized.
 *
 *    All other fields on tensor are always initialized.  In particular,
 *    size is always valid. (Historically, a tensor declared as Tensor x(CPU)
 *    also had uninitialized size, encoded as numel == -1, but we have now
 *    decided to default to zero size, resulting in numel == 0).
 *
 *    Uninitialized storages MUST be uniquely owned, to keep our model
 *    simple.  Thus, we will reject operations which could cause an
 *    uninitialized storage to become shared (or a shared storage to
 *    become uninitialized, e.g., from FreeMemory).
 *
 *    In practice, tensors which are storage-UNINITIALIZED and
 *    dtype-UNINITIALIZED are *extremely* ephemeral: essentially,
 *    after you do a Resize(), you basically always call mutable_data()
 *    immediately afterwards.  Most functions are not designed to
 *    work if given a storage-UNINITIALIZED, dtype-UNINITIALIZED tensor.
 *
 *    We intend to eliminate all uninitialized states, so that every
 *    tensor is fully initialized in all fields.  Please do not write new code
 *    that depends on these uninitialized states.
 */
@Namespace("c10") @NoOffset @Properties(inherit = org.bytedeco.pytorch.presets.torch.class)
public class TensorImpl extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TensorImpl(Pointer p) { super(p); }

  
  // Note [Enum ImplType]
  // This enum is temporary. In the followup refactor we should
  // think about how to specialize TensorImpl creation for view
  // tensors. Currently we only special case its key_set_ but
  // there's also potential to share version_counter_ directly
  // without creating first and then override in as_view.
  public enum ImplType { VIEW(0);

      public final int value;
      private ImplType(int v) { this.value = v; }
      private ImplType(ImplType e) { this.value = e.value; }
      public ImplType intern() { for (ImplType e : values()) if (e.value == value) return e; return this; }
      @Override public String toString() { return intern().name(); }
  }

  /**
   * Construct a 1-dim 0-size tensor backed by the given storage.
   */
  public TensorImpl(
        @Cast({"", "c10::Storage&&"}) @StdMove Storage storage,
        @ByVal DispatchKeySet arg1,
        @Const @ByVal TypeMeta data_type) { super((Pointer)null); allocate(storage, arg1, data_type); }
  private native void allocate(
        @Cast({"", "c10::Storage&&"}) @StdMove Storage storage,
        @ByVal DispatchKeySet arg1,
        @Const @ByVal TypeMeta data_type);

  // See Note [Enum ImplType]
  public TensorImpl(
        ImplType arg0,
        @Cast({"", "c10::Storage&&"}) @StdMove Storage storage,
        @ByVal DispatchKeySet arg2,
        @Const @ByVal TypeMeta data_type) { super((Pointer)null); allocate(arg0, storage, arg2, data_type); }
  private native void allocate(
        ImplType arg0,
        @Cast({"", "c10::Storage&&"}) @StdMove Storage storage,
        @ByVal DispatchKeySet arg2,
        @Const @ByVal TypeMeta data_type);
  public TensorImpl(
        @Cast("c10::TensorImpl::ImplType") int arg0,
        @Cast({"", "c10::Storage&&"}) @StdMove Storage storage,
        @ByVal DispatchKeySet arg2,
        @Const @ByVal TypeMeta data_type) { super((Pointer)null); allocate(arg0, storage, arg2, data_type); }
  private native void allocate(
        @Cast("c10::TensorImpl::ImplType") int arg0,
        @Cast({"", "c10::Storage&&"}) @StdMove Storage storage,
        @ByVal DispatchKeySet arg2,
        @Const @ByVal TypeMeta data_type);

  /**
   * Construct a 1-dim 0 size tensor that doesn't have a storage.
   */
  public TensorImpl(
        @ByVal DispatchKeySet arg0,
        @Const @ByVal TypeMeta data_type,
        @ByVal DeviceOptional device_opt) { super((Pointer)null); allocate(arg0, data_type, device_opt); }
  private native void allocate(
        @ByVal DispatchKeySet arg0,
        @Const @ByVal TypeMeta data_type,
        @ByVal DeviceOptional device_opt);

  // Legacy constructors so I don't have to go update call sites.
  // TODO: When Variable is added, delete these constructors
  public TensorImpl(
        @Cast({"", "c10::Storage&&"}) @StdMove Storage storage,
        DispatchKey dispatch_key,
        @Const @ByVal TypeMeta data_type) { super((Pointer)null); allocate(storage, dispatch_key, data_type); }
  private native void allocate(
        @Cast({"", "c10::Storage&&"}) @StdMove Storage storage,
        DispatchKey dispatch_key,
        @Const @ByVal TypeMeta data_type);
  public TensorImpl(
        @Cast({"", "c10::Storage&&"}) @StdMove Storage storage,
        @Cast("c10::DispatchKey") byte dispatch_key,
        @Const @ByVal TypeMeta data_type) { super((Pointer)null); allocate(storage, dispatch_key, data_type); }
  private native void allocate(
        @Cast({"", "c10::Storage&&"}) @StdMove Storage storage,
        @Cast("c10::DispatchKey") byte dispatch_key,
        @Const @ByVal TypeMeta data_type);
  public TensorImpl(
        DispatchKey dispatch_key,
        @Const @ByVal TypeMeta data_type,
        @ByVal DeviceOptional device_opt) { super((Pointer)null); allocate(dispatch_key, data_type, device_opt); }
  private native void allocate(
        DispatchKey dispatch_key,
        @Const @ByVal TypeMeta data_type,
        @ByVal DeviceOptional device_opt);
  public TensorImpl(
        @Cast("c10::DispatchKey") byte dispatch_key,
        @Const @ByVal TypeMeta data_type,
        @ByVal DeviceOptional device_opt) { super((Pointer)null); allocate(dispatch_key, data_type, device_opt); }
  private native void allocate(
        @Cast("c10::DispatchKey") byte dispatch_key,
        @Const @ByVal TypeMeta data_type,
        @ByVal DeviceOptional device_opt);
  
  
  
  

  /**
   * Release (decref) storage, and any other external allocations.  This
   * override is for {@code intrusive_ptr_target} and is used to implement weak
   * tensors.
   */
  public native void release_resources();

  /**
   * Return the DispatchKeySet corresponding to this Tensor, specifying
   * all of the DispatchKeys that this Tensor identifies as.  This is the
   * information used to dispatch operations on this tensor.
   */
  public native @ByVal DispatchKeySet key_set();

  /**
   * Return a reference to the sizes of this tensor.  This reference remains
   * valid as long as the tensor is live and not resized.
   */
  public native @ByVal @Cast("c10::ArrayRef<int64_t>*") LongArrayRef sizes();
// #endif

  /**
   * Return a reference to the strides of this tensor.  This reference remains
   * valid as long as the tensor is live and not restrided.
   */
  public native @ByVal @Cast("c10::ArrayRef<int64_t>*") LongArrayRef strides();

  /**
   * Return the number of dimensions of this tensor.  Note that 0-dimension
   * represents a Tensor that is a Scalar, e.g., one that has a single element.
   */
  public native @Cast("int64_t") long dim();
// #endif

  /**
   * True if this tensor has storage. See storage() for details.
   */
// #ifdef DEBUG
  // Allow subclasses to check that their storage_ is never getting set in debug
  // builds.
  public native @Cast("bool") boolean has_storage();
// #endif

  /**
   * Return the underlying storage of a Tensor.  Multiple tensors may share
   * a single storage.  A Storage is an impoverished, Tensor-like class
   * which supports far less operations than Tensor.
   *
   * Avoid using this method if possible; try to use only Tensor APIs to perform
   * operations.
   */
  public native @Cast({"", "c10::Storage&&"}) @StdMove Storage storage();

  /**
   * The number of elements in a tensor.
   *
   * WARNING: Previously, if you were using the Caffe2 API, you could
   * test numel() == -1 to see if a tensor was uninitialized.  This
   * is no longer true; numel always accurately reports the product
   * of sizes of a tensor.
   */
  public native @Cast("int64_t") long numel();

  public native @Cast("bool") boolean unique_version();

  /**
   * Whether or not a tensor is laid out in contiguous memory.
   *
   * Tensors with non-trivial strides are not contiguous.  See
   * compute_contiguous() for the exact definition of whether or not
   * a tensor is contiguous or not.
   *
   * NOTE: is_contiguous is only {@code TENSORIMPL_MAYBE_VIRTUAL} for
   * backward compatibility. See {@code set_has_contiguity_policy} and
   * {@code is_contiguous_custom} for the encouraged customization point.
   */
  public native @Cast("bool") boolean is_contiguous(
        @ByVal(nullValue = "at::MemoryFormat::Contiguous") MemoryFormat memory_format);
  public native @Cast("bool") boolean is_contiguous();
  public native @Cast("bool") boolean is_sparse();

  // Whether a tensor is sparse COO or not. Use is_sparse_csr for checking CSR
  // format.
  public native @Cast("bool") boolean is_sparse_csr();

  public native @Cast("bool") boolean is_quantized();

  public native @Cast("bool") boolean is_meta();

  public native @Cast("bool") boolean is_cpu();

  public native @Cast("bool") boolean is_cuda();

  public native @Cast("bool") boolean is_xpu();

  public native @Cast("bool") boolean is_xla();

  public native @Cast("bool") boolean is_hip();

  public native @Cast("bool") boolean is_mkldnn();

  public native @Cast("bool") boolean is_vulkan();

  public native @Cast("bool") boolean is_metal();

  public native @Cast("bool") boolean is_mlc();

  // TODO: remove this once we don't automatically enabled Autograd dispatch
  // keys
  //       in TensorImpl constructor.
  // DON'T USE THIS API!! It's only created for testing purpose in
  // file aten/src/ATen/core/boxing/impl/test_helpers.h
  public native void remove_autograd_key();

  // Inference tensor doesn't have autograd or ADInplaceOrView key.
  // Invariant:
  //   Inference tensor has version_counter_.enabled() == false
  public native @Cast("bool") boolean is_inference_tensor();

  public native @Cast("int64_t") long get_device();

  public native @ByVal Device device();

  public native Layout layout();

  /**
   * True if a tensor was auto-wrapped from a C++ or Python number.
   * For example, when you write 't + 2', 2 is auto-wrapped into a Tensor
   * with {@code is_wrapped_number_} set to true.
   *
   * Wrapped numbers do not participate in the result type computation for
   * mixed-type operations if there are any Tensors that are not wrapped
   * numbers.  This is useful, because we want 't + 2' to work with
   * any type of tensor, not just LongTensor (which is what integers
   * in Python represent).
   *
   * Otherwise, they behave like their non-wrapped equivalents.
   * See [Result type computation] in TensorIterator.h.
   *
   * Why did we opt for wrapped numbers, as opposed to just having
   * an extra function add(Tensor, Scalar)?  This helps greatly reduce
   * the amount of code we have to write for add, when actually
   * a Tensor-Scalar addition is really just a Tensor-Tensor
   * addition when the RHS is 0-dim (except for promotion behavior.)
   */
  public native @Cast("bool") boolean is_wrapped_number();

  /**
   * Set whether or not a tensor was auto-wrapped from a C++ or Python
   * number.  You probably don't want to call this, unless you are
   * writing binding code.
   */
  public native void set_wrapped_number(@Cast("bool") boolean value);

  /**
   * Returns true if Tensor supports as_strided and as_strided_backward.
   * This is used in autograd to perform inplace update on view Tensors.
   * See Note [View + Inplace update for base tensor] and
   * [View + Inplace update for view tensor] for details.
   * Note this method only returns true for XLA backend, where it
   * simulates strided Tensor to support most view ops, but it cannot
   * fully support general {@code as_strided} case.
   * It can be expanded as needed in the future, e.g sparse Tensor.
   */
  public native @Cast("bool") boolean support_as_strided();

  // ~~~~~ Autograd API ~~~~~
  // Some methods below are defined in TensorImpl.cpp because Tensor is an
  // incomplete type.

  /**
   * Set whether or not a tensor requires gradient.
   */
  public native void set_requires_grad(@Cast("bool") boolean requires_grad);

  /**
   * True if a tensor requires gradient.  Tensors which require gradient
   * have history tracked for any operations performed on them, so that
   * we can automatically differentiate back to them.  A tensor that
   * requires gradient and has no history is a "leaf" tensor, which we
   * accumulate gradients into.
   */
  public native @Cast("bool") boolean requires_grad();

  /**
   * Return a mutable reference to the gradient.  This is conventionally
   * used as {@code t.grad() = x} to set a gradient to a completely new tensor.
   */
  public native @ByRef Tensor mutable_grad();

  /**
   * Return the accumulated gradient of a tensor.  This gradient is written
   * into when performing backwards, when this tensor is a leaf tensor.
   */
  public native @Const @ByRef Tensor grad();

  /**
   * Return the accumulated gradient of a tensor. This gradient is computed
   * using forward mode AD.
   *
   * This is an internal API that should never be used by end users.
   *
   * The API is as follows:
   *   - "level" allows to specify the level of forward AD nesting for which the
   *     gradient should be returned. Note that since levels are not fully
   *     supported yet, this argument should be 0. See documentation for
   *     torch::autograd::enter_dual_level for more details about forward AD
   * nesting.
   *   - "self" should represent the Tensor whose forward grad is accessed. It
   * is required when dealing with view.
   */
  public native @Const @ByRef Tensor _fw_grad(@Cast("uint64_t") long level, @Const @ByRef Tensor self);

  /**
   * Sets the forward gradient for this Tensor.
   * The given Tensor might not be used directly and its content will be copied.
   *
   * This is an internal API that should never be used by end users.
   *
   * The API is as follows:
   *   - "new_grad" is a Tensor containing the new value of the gradient that
   * should be set
   *   - "self" should represent the Tensor whose forward grad is accessed. It
   * is required when dealing with view.
   *   - "level" allows to specify the level of forward AD nesting for which the
   *     gradient should be set. Note that since levels are not fully supported
   *     yet, this argument should be 0. See documentation for
   * torch::autograd::enter_dual_level for more details about forward AD
   * nesting.
   *   - "is_inplace_op" is a boolean flag that tells if this gradient was
   * generated by an inplace operation or an out of place one. This allows
   * better error checking.
   */
  public native void _set_fw_grad(
        @Const @ByRef Tensor new_grad,
        @Const @ByRef Tensor self,
        @Cast("uint64_t") long level,
        @Cast("bool") boolean is_inplace_op);

  /**
   * Return a typed data pointer to the actual data which this tensor refers to.
   * This checks that the requested type (from the template parameter) matches
   * the internal type of the tensor.
   *
   * It is invalid to call data() on a dtype-uninitialized tensor, even if
   * the size is 0.
   *
   * WARNING: If a tensor is not contiguous, you MUST use strides when
   * performing index calculations to determine the location of elements in
   * the tensor.  We recommend using 'TensorAccessor' to handle this computation
   * for you; this class is available from 'Tensor'.
   */

  /**
   * More efficient helper for Tensor::data_ptr(). Like data<T>(), but
   * does not do a type check. Unlike the untemplated data(), does
   * check has_storage() and storage_initialized().
   */

  /**
   * Return a void* data pointer to the actual data which this tensor refers to.
   *
   * It is invalid to call data() on a dtype-uninitialized tensor, even if the
   * size is 0.
   *
   * WARNING: The data pointed to by this tensor may not contiguous; do NOT
   * assume that itemsize() * numel() is sufficient to compute the bytes that
   * can be validly read from this tensor.
   */
  public native Pointer data();

  /**
   * Like data<T>(), but performs no checks.  You are responsible for ensuring
   * that all invariants required by data() are upheld here.
   */

  /**
   * Returns the TypeMeta of a tensor, which describes what data type
   * it is (e.g., int, float, ...)
   */
  public native @Const @ByVal TypeMeta dtype();

  /**
   * Return the size of a single element of this tensor in bytes.
   */
  public native @Cast("size_t") long itemsize();

  /**
   * Return the offset in number of elements into the storage that this
   * tensor points to.  Most tensors have storage_offset() == 0, but,
   * for example, an index into a tensor will have a non-zero storage_offset().
   *
   * WARNING: This is NOT computed in bytes.
   */
  public native @Cast("int64_t") long storage_offset();
  /**
   * True if a tensor has no elements (e.g., numel() == 0).
   */
  public native @Cast("bool") boolean is_empty();

  /**
   * Change the size at some dimension.  This DOES NOT update strides;
   * thus, most changes to size will not preserve contiguity.  You probably
   * also want to call set_stride() when you call this.
   *
   * TODO: This should be jettisoned in favor of {@code set_sizes_and_strides},
   * which is harder to misuse.
   */
  public native void set_size(@Cast("int64_t") long dim, @Cast("int64_t") long new_size);

  /**
   * Change the stride at some dimension.
   *
   * TODO: This should be jettisoned in favor of {@code set_sizes_and_strides},
   * which is harder to misuse.
   */
  public native void set_stride(@Cast("int64_t") long dim, @Cast("int64_t") long new_stride);

  /**
   * Set the offset into the storage of this tensor.
   *
   * WARNING: This does NOT check if the tensor is in bounds for the new
   * location at the storage; the caller is responsible for checking this
   * (and resizing if necessary.)
   */
  public native void set_storage_offset(@Cast("int64_t") long storage_offset);

  /**
   * Like set_sizes_and_strides but assumes contiguous strides.
   *
   * WARNING: This function does not check if the requested
   * sizes/strides are in bounds for the storage that is allocated;
   * this is the responsibility of the caller
   */
  public native void set_sizes_contiguous(@ByVal @Cast("c10::ArrayRef<int64_t>*") LongArrayRef new_size);
  public native void set_sizes_contiguous(@ByVal @Cast({"int64_t*", "std::vector<int64_t>&"}) @StdVector long... new_size);

  /**
   * Set the sizes and strides of a tensor.
   *
   * WARNING: This function does not check if the requested
   * sizes/strides are in bounds for the storage that is allocated;
   * this is the responsibility of the caller
   */
  public native void set_sizes_and_strides(@ByVal @Cast("c10::ArrayRef<int64_t>*") LongArrayRef new_size, @ByVal @Cast("c10::ArrayRef<int64_t>*") LongArrayRef new_stride);
  public native void set_sizes_and_strides(@ByVal @Cast({"int64_t*", "std::vector<int64_t>&"}) @StdVector long[] new_size, @ByVal @Cast({"int64_t*", "std::vector<int64_t>&"}) @StdVector long... new_stride);

  /**
   * Return the size of a tensor at some dimension.
   */
  public native @Cast("int64_t") long size(@Cast("int64_t") long d);

  /**
   * Return the stride of a tensor at some dimension.
   */
  public native @Cast("int64_t") long stride(@Cast("int64_t") long d);

  /**
   * Set whether a tensor allows changes to its metadata (e.g. sizes / strides /
   * storage / storage_offset). See NOTE [ Metadata Change for a Detached Tensor
   * ] for details.
   */
  public native void set_allow_tensor_metadata_change(@Cast("bool") boolean value);

  /**
   * True if a tensor allows changes to its metadata (e.g. sizes / strides /
   * storage / storage_offset). See NOTE [ Metadata Change for a Detached Tensor
   * ] for details.
   */
  public native @Cast("bool") boolean allow_tensor_metadata_change();

  /**
   * Set the pointer to autograd metadata.
   */
  public native void set_autograd_meta(
        @UniquePtr AutogradMetaInterface autograd_meta);

  /**
   * Return the pointer to autograd metadata.  May return nullptr if the
   * tensor does not track gradients.
   */
  public native AutogradMetaInterface autograd_meta();

  /**
   * Set the pointer to named tensor metadata.
   */
  public native void set_named_tensor_meta(
        @UniquePtr NamedTensorMetaInterface named_tensor_meta);

  /**
   * Return the pointer to named tensor metadata.
   */

  public native NamedTensorMetaInterface named_tensor_meta();

  public native @Cast("bool") boolean has_named_tensor_meta();

  // NOTE [ TensorImpl Shallow-Copying ]
  //
  // TensorImpl shallow-copying is used when we want to have two Variables share
  // the same tensor metadata (e.g. sizes / strides / storage pointer /
  // storage_offset), but each with a different autograd history. Example call
  // sites:
  //
  // 1. `var_detached = var.detach()` uses `shallow_copy_and_detach()` to create
  // `var_detached` that shares the same tensor metadata with `var`, but with a
  // completely new autograd history.
  // 2. `var.set_data(tensor)` uses `shallow_copy_from()` to copy tensor
  // metadata from `tensor` into `var`, while keeping `var`'s original
  // AutogradMeta.
  //
  // Functions that shallow-copy a TensorImpl (such as
  // `shallow_copy_and_detach()` / `shallow_copy_from()` /
  // `copy_tensor_metadata()`) copy the tensor metadata fields (e.g. sizes /
  // strides / storage pointer / storage_offset) by value. However, the
  // following fields are not copied:
  //
  // 1. the AutogradMeta pointer, because it is unique for each Variable.
  // 2. the version counter, because the destination TensorImpl's version
  // counter is either set to the passed-in `version_counter` (in
  // `shallow_copy_and_detach()` and `copy_tensor_metadata()`), or it is kept
  // intact (in `shallow_copy_from()`). See NOTE [ Version Counter Sharing ] for
  // details.
  //
  // In `shallow_copy_and_detach()` and `copy_tensor_metadata()`, the passed-in
  // `allow_tensor_metadata_change` determines whether the TensorImpl
  // shallow-copy allows changes to its metadata (e.g. sizes / strides / storage
  // / storage_offset). See NOTE [ Metadata Change for a Detached Tensor ] for
  // details.
  //
  // In `shallow_copy_from()`, we don't check the destination TensorImpl's
  // `allow_tensor_metadata_change_`, because `shallow_copy_from()` is used for
  // implementing functions such as `var.set_data(tensor)`, which changes
  // `var`'s tensor metadata and expects its `allow_tensor_metadata_change_` to
  // be ignored.

  /**
   * One TensorImpl can be copied to another TensorImpl if they have the same
   * DispatchKeySet. The only two special cases (for legacy reason) are:
   * CPU is compatible with CUDA and SparseCPU is
   * compatible with SparseCUDA.
   */
  public native @Cast("bool") boolean has_compatible_shallow_copy_type(@ByVal DispatchKeySet from);

  /**
   * Return a TensorImpl that is a shallow-copy of this TensorImpl.
   *
   * For usage of {@code version_counter} and {@code allow_tensor_metadata_change},
   * see NOTE [ TensorImpl Shallow-Copying ].
   */

  /**
   * Return a TensorImpl that is a shallow-copy of this TensorImpl.
   *
   * For usage of {@code version_counter} and {@code allow_tensor_metadata_change},
   * see NOTE [ TensorImpl Shallow-Copying ].
   */

  /**
   * Shallow-copies data from another TensorImpl into this TensorImpl.
   *
   * For why this function doesn't check this TensorImpl's
   * {@code allow_tensor_metadata_change_}, see NOTE [ TensorImpl Shallow-Copying ].
   */

  // Inference tensor doesn't have version counter,
  // set_version_counter is no-op for them.
  public native void set_version_counter(@Const @ByRef VariableVersion version_counter);

  public native @Const @ByRef @NoException(true) VariableVersion version_counter();

  public native void bump_version();

  public native @NoException(true) void set_pyobj(@Cast("PyObject*") Pointer pyobj);

  public native @Cast("PyObject*") @NoException(true) Pointer pyobj();
  /**
   * The device type of a Tensor, e.g., DeviceType::CPU or DeviceType::CUDA.
   */
  public native DeviceType device_type();

  /**
   * \brief Extends the outer-most dimension of this tensor by num elements,
   * preserving the existing data.
   *
   * The underlying data may be reallocated in order to accommodate the new
   * elements, in which case this tensors' capacity is grown at a factor of
   * growthPct. This ensures that Extend runs on an amortized O(1) time
   * complexity.
   *
   * This op is auto-asynchronous if the underlying device (CUDA) supports it.
   */
  public native void Extend(@Cast("int64_t") long num, float growthPct);

  /**
   * \brief Reserve space for the underlying tensor.
   *
   * This must be called after Resize(), since we only specify the first
   * dimension This does not copy over the old data to the newly allocated space
   */

  /**
   * \brief Resizes a tensor.
   *
   * Resize takes in a vector of ints specifying the dimensions of the tensor.
   * You can pass in an empty vector to specify that it is a scalar (i.e.
   * containing one single item).
   *
   * The underlying storage may be deleted after calling Resize: if the new
   * shape leads to a different number of items in the tensor, the old memory
   * is deleted and new memory will be allocated next time you call
   * mutable_data(). However, if the shape is different but the total number of
   * items is the same, the underlying storage is kept.
   *
   * This method respects caffe2_keep_on_shrink.  Consult the internal logic
   * of this method to see exactly under what circumstances this flag matters.
   */

  /**
   * Resizes the tensor without touching underlying storage.
   * This requires the total size of the tensor to remains constant.
   */
  public native void Reshape(@Cast("const std::vector<int64_t>*") @ByRef LongVector dims);

  /**
   * Release whatever memory the tensor was holding but keep size and type
   * information. Subsequent call to mutable_data will trigger new memory
   * allocation.
   */
  public native void FreeMemory();

  /**
   * \brief Shares the data with another tensor.
   *
   * To share data between two tensors, the sizes of the two tensors must be
   * equal already. The reason we do not implicitly do a Resize to make the two
   * tensors have the same shape is that we want to allow tensors of different
   * shapes but the same number of items to still be able to share data. This
   * allows one to e.g. have a n-dimensional Tensor and a flattened version
   * sharing the same underlying storage.
   *
   * The source tensor should already have its data allocated.
   */
  // To be deprecated
  public native void ShareData(@Const @ByRef TensorImpl src);

  public native void ShareExternalPointer(
        @Cast({"", "c10::DataPtr&&"}) @StdMove DataPtr data_ptr,
        @Const @ByVal TypeMeta data_type,
        @Cast("size_t") long size_bytes);

  /**
   * Returns a mutable raw pointer of the underlying storage. Since we will need
   * to know the type of the data for allocation, a TypeMeta object is passed in
   * to specify the necessary information. This is conceptually equivalent of
   * calling mutable_data<T>() where the TypeMeta parameter meta is derived from
   * the type T. This function differs from mutable_data<T>() in the sense that
   * the type T can be specified during runtime via the TypeMeta object.
   *
   * If the existing data does not match the desired type, it will be deleted
   * and a new storage will be created.
   */
  public native Pointer raw_mutable_data(@Const @ByVal TypeMeta meta);

  /**
   * Returns a typed pointer of the underlying storage.
   *
   * For fundamental types, we reuse possible existing storage if there
   * is sufficient capacity.
   */

  /**
   * True if a tensor is storage initialized.  A tensor may become
   * storage UNINITIALIZED after a Resize() or FreeMemory()
   */
  public native @Cast("bool") boolean storage_initialized();

  /**
   * True if a tensor is dtype initialized.  A tensor allocated with
   * Caffe2-style constructors is dtype uninitialized until the
   * first time mutable_data<T>() is called.
   */
  public native @Cast("bool") @NoException(true) boolean dtype_initialized();

  public native void set_storage_keep_dtype(@Cast({"", "c10::Storage&&"}) @StdMove Storage storage);

  public native void set_storage_and_dtype(
        @Cast({"", "c10::Storage&&"}) @StdMove Storage storage,
        @Const @ByVal TypeMeta data_type);

  /**
   * Set the strides of the tensor to match memory_format
   *
   * WARNING: This function doesn't rearrange data and assumes tensor is a
   * memory contiguous
   */
  public native void empty_tensor_restride(MemoryFormat memory_format);
  public native void empty_tensor_restride(@Cast("c10::MemoryFormat") byte memory_format);

  public native @Cast("bool") boolean is_strides_like_channels_last();

  public native @Cast("bool") boolean is_strides_like_channels_last_3d();

  public native @Cast("bool") boolean is_non_overlapping_and_dense();
  public native void set_storage_access_should_throw();
}
