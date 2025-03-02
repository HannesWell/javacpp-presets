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


/** An OrderedDict of {@code Module}s that registers its elements by their {@code key}s.
 * 
 *  \rst
 *  .. code-block:: cpp
 * 
 *    torch::OrderedDict<std::string, std::shared_ptr<Module>> ordereddict = {
 *      {"linear", Linear(10, 3).ptr()},
 *      {"conv", Conv2d(1, 2, 3).ptr()},
 *      {"dropout", Dropout(0.5).ptr()},
 *    };
 *    torch::nn::ModuleDict dict1(ordereddict);
 * 
 *    for (const auto &module : *dict1) {
 *      module->pretty_print(std::cout);
 *    }
 * 
 *    std::vector<std::pair<std::string, std::shared_ptr<Module>>> list = {
 *      {"linear", Linear(10, 3).ptr()},
 *      {"conv", Conv2d(1, 2, 3).ptr()},
 *      {"dropout", Dropout(0.5).ptr()},
 *    };
 *    torch::nn::ModuleDict dict2(list);
 * 
 *    for (const auto &module : *dict2) {
 *      module->pretty_print(std::cout);
 *    }
 * 
 *  \endrst
 * 
 *  Why should you use {@code ModuleDict} instead of a simple {@code map} or {@code OrderedDict}?
 *  The value a {@code ModuleDict} provides over manually calling an ordered map of
 *  modules is that it allows treating the whole container *as a single module*,
 *  such that performing a transformation on the {@code ModuleDict} applies to each of the
 *  modules it stores (which are each a registered submodule of the {@code ModuleDict}).
 *  For example, calling {@code .to(torch::kCUDA)} on a {@code ModuleDict} will move each module
 *  in the map to CUDA memory. For example:
 * 
 *  \rst
 *  .. code-block:: cpp
 * 
 *    torch::OrderedDict<std::string, std::shared_ptr<Module>> ordereddict = {
 *      {"linear", Linear(10, 3).ptr()},
 *      {"conv", Conv2d(1, 2, 3).ptr()},
 *      {"dropout", Dropout(0.5).ptr()},
 *    };
 *    torch::nn::ModuleDict dict(ordereddict);
 * 
 *    // Convert all modules to CUDA.
 *    dict->to(torch::kCUDA);
 * 
 *  \endrst
 * 
 *  Finally, {@code ModuleDict} provides a lightweight container API, such as allowing
 *  iteration over submodules, positional access, adding new modules from a vector
 *  of key-module pairs or an {@code OrderedDict} or another {@code ModuleDict} after
 *  construction via {@code update}. */
// NOLINTNEXTLINE(bugprone-exception-escape)
@Namespace("torch::nn") @NoOffset @Properties(inherit = org.bytedeco.pytorch.presets.torch.class)
public class ModuleDictImpl extends ModuleDictImplCloneable {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public ModuleDictImpl(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public ModuleDictImpl(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public ModuleDictImpl position(long position) {
        return (ModuleDictImpl)super.position(position);
    }
    @Override public ModuleDictImpl getPointer(long i) {
        return new ModuleDictImpl((Pointer)this).offsetAddress(i);
    }


  public ModuleDictImpl() { super((Pointer)null); allocate(); }
  @NoDeallocator private native void allocate();

  /** Constructs the {@code ModuleDict} from a list of string-Module pairs. */
  public ModuleDictImpl(
        @Const @ByRef StringSharedModulePairVector modules) { super((Pointer)null); allocate(modules); }
  @NoDeallocator private native void allocate(
        @Const @ByRef StringSharedModulePairVector modules);

  /** Constructs the {@code ModuleDict} from an {@code OrderedDict}. */
  public ModuleDictImpl(
        @Const @ByRef StringSharedModuleDict modules) { super((Pointer)null); allocate(modules); }
  @NoDeallocator private native void allocate(
        @Const @ByRef StringSharedModuleDict modules);

  /** Return the items in the {@code ModuleDict}. */
  public native @ByVal StringSharedModulePairVector items();

  /** Return the keys in the {@code ModuleDict}. */
  public native @ByVal StringVector keys();

  /** Return the values in the {@code ModuleDict}. */
  public native @ByVal SharedModuleVector values();

  /** Return an iterator to the start of {@code ModuleDict}. */
  public native @ByVal @Cast("torch::nn::ModuleDictImpl::Iterator*") StringSharedModuleDict.Iterator begin();

  /** Return a const iterator to the start of {@code ModuleDict}. */

  /** Return an iterator to the end of {@code ModuleDict}. */
  public native @ByVal @Cast("torch::nn::ModuleDictImpl::Iterator*") StringSharedModuleDict.Iterator end();

  /** Return a const iterator to the end of {@code ModuleDict}. */

  /** Return the number of items currently stored in the {@code ModuleDict}. */
  public native @Cast("size_t") @NoException(true) long size();

  /** Return true if the {@code ModuleDict} is empty, otherwise return false. */
  public native @Cast("bool") @NoException(true) boolean empty();

  /** Check if the centain parameter with the key in the {@code ModuleDict}. */
  public native @Cast("bool") @NoException(true) boolean contains(@StdString BytePointer key);
  public native @Cast("bool") @NoException(true) boolean contains(@StdString String key);

  /** Remove all items from the {@code ModuleDict}. */
  public native void clear();

  /** Special cloning function for {@code ModuleDict} because it does not use
   *  {@code reset()}. */
  public native @SharedPtr @Cast({"", "std::shared_ptr<torch::nn::Module>"}) Module clone(
        @Const @ByRef(nullValue = "c10::optional<c10::Device>(c10::nullopt)") DeviceOptional device);
  public native @SharedPtr @Cast({"", "std::shared_ptr<torch::nn::Module>"}) Module clone();

  /** {@code reset()} is empty for {@code ModuleDict}, since it does not have parameters of
   *  its own. */
  public native void reset();

  /** Pretty prints the {@code ModuleDict} into the given {@code stream}. */
  public native void pretty_print(@Cast("std::ostream*") @ByRef Pointer stream);

  /** Attempts to returns the {@code Module} associated with the given {@code key}. Throws
   *  an exception if no such {@code key} is stored in the {@code ModuleDict}. Check
   *  contains(key) before for a non-throwing way of access. */
  public native @SharedPtr @Name("operator []") @Cast({"", "std::shared_ptr<torch::nn::Module>"}) Module get(@StdString BytePointer key);
  public native @SharedPtr @Name("operator []") @Cast({"", "std::shared_ptr<torch::nn::Module>"}) Module get(@StdString String key);

  /** Attempts to return the module at the given key as the requested type.
   *  Throws an exception if no such {@code key} is stored in the {@code ModuleDict}.
   *  Check contains(key) before for a non-throwing way of access. */

  /** Attempts to return the module at the given key as the requested type.
   *  Throws an exception if no such {@code key} is stored in the {@code ModuleDict}.
   *  Check contains(key) before for a non-throwing way of access. */

  /** Removes and returns the {@code Module} associated with the given {@code key}.
   *  Throws an exception if no such {@code key} is stored in the {@code ModuleDict}.
   *  Check contains(key) before for a non-throwing way of access. */
  public native @SharedPtr @Cast({"", "std::shared_ptr<torch::nn::Module>"}) Module pop(@StdString BytePointer key);
  public native @SharedPtr @Cast({"", "std::shared_ptr<torch::nn::Module>"}) Module pop(@StdString String key);

  /** Updated the {@code ModuleDict} with a vector of key-module pairs. */
  public native void update(
        @Const @ByRef StringSharedModulePairVector modules);

  /** Updated the {@code ModuleDict} with key-value pairs from {@code OrderedDict} or {@code ModuleDict}. */
  /** Private {@code OrderedDict} holding the key-Module pairs. */

}
