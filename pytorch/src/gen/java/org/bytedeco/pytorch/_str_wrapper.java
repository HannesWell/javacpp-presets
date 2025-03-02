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


// Specializations for already-a-string types.
@Name("c10::detail::_str_wrapper<std::string>") @Properties(inherit = org.bytedeco.pytorch.presets.torch.class)
public class _str_wrapper extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public _str_wrapper() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public _str_wrapper(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public _str_wrapper(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public _str_wrapper position(long position) {
        return (_str_wrapper)super.position(position);
    }
    @Override public _str_wrapper getPointer(long i) {
        return new _str_wrapper((Pointer)this).offsetAddress(i);
    }

  // return by reference to avoid the binary size of a string copy
  public static native @StdString BytePointer call(@StdString BytePointer str);
  public static native @StdString String call(@StdString String str);
}
