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


// This struct holds the actual type information. There will be
// one allocated per type. TypeMeta objects will then point to the struct
// instance for the type they're configured for.
@Namespace("caffe2::detail") @NoOffset @Properties(inherit = org.bytedeco.pytorch.presets.torch.class)
public class TypeMetaData extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TypeMetaData(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public TypeMetaData(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public TypeMetaData position(long position) {
        return (TypeMetaData)super.position(position);
    }
    @Override public TypeMetaData getPointer(long i) {
        return new TypeMetaData((Pointer)this).offsetAddress(i);
    }

  public static class New extends FunctionPointer {
      static { Loader.load(); }
      /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
      public    New(Pointer p) { super(p); }
      protected New() { allocate(); }
      private native void allocate();
      public native Pointer call();
  }
  public static class PlacementNew extends FunctionPointer {
      static { Loader.load(); }
      /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
      public    PlacementNew(Pointer p) { super(p); }
      protected PlacementNew() { allocate(); }
      private native void allocate();
      public native void call(Pointer arg0, @Cast("size_t") long arg1);
  }
  public static class Copy extends FunctionPointer {
      static { Loader.load(); }
      /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
      public    Copy(Pointer p) { super(p); }
      protected Copy() { allocate(); }
      private native void allocate();
      public native void call(@Const Pointer arg0, Pointer arg1, @Cast("size_t") long arg2);
  }
  public static class PlacementDelete extends FunctionPointer {
      static { Loader.load(); }
      /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
      public    PlacementDelete(Pointer p) { super(p); }
      protected PlacementDelete() { allocate(); }
      private native void allocate();
      public native void call(Pointer arg0, @Cast("size_t") long arg1);
  }
  public static class Delete extends FunctionPointer {
      static { Loader.load(); }
      /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
      public    Delete(Pointer p) { super(p); }
      protected Delete() { allocate(); }
      private native void allocate();
      public native void call(Pointer arg0);
  }

  public TypeMetaData() { super((Pointer)null); allocate(); }
  @NoException(true) private native void allocate();

  public TypeMetaData(
        @Cast("size_t") long itemsize,
        New newFn,
        PlacementNew placementNew,
        Copy copy,
        PlacementDelete placementDelete,
        Delete deleteFn,
        @ByVal TypeIdentifier id,
        @ByVal @Cast("c10::string_view*") Pointer name) { super((Pointer)null); allocate(itemsize, newFn, placementNew, copy, placementDelete, deleteFn, id, name); }
  @NoException(true) private native void allocate(
        @Cast("size_t") long itemsize,
        New newFn,
        PlacementNew placementNew,
        Copy copy,
        PlacementDelete placementDelete,
        Delete deleteFn,
        @ByVal TypeIdentifier id,
        @ByVal @Cast("c10::string_view*") Pointer name);

  public native @Cast("size_t") long itemsize_(); public native TypeMetaData itemsize_(long setter);
  public native New new_(); public native TypeMetaData new_(New setter);
  public native PlacementNew placementNew_(); public native TypeMetaData placementNew_(PlacementNew setter);
  public native Copy copy_(); public native TypeMetaData copy_(Copy setter);
  public native PlacementDelete placementDelete_(); public native TypeMetaData placementDelete_(PlacementDelete setter);
  public native Delete delete_(); public native TypeMetaData delete_(Delete setter);
  public native @ByRef TypeIdentifier id_(); public native TypeMetaData id_(TypeIdentifier setter);
  public native @ByRef @Cast("c10::string_view*") Pointer name_(); public native TypeMetaData name_(Pointer setter);
}
