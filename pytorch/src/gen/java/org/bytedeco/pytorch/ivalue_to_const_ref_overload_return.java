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

// Determine the return type of `IValue::to() const &`. It's a const
// reference when possible and a copy otherwise. It is in this
// separate header so that List can use it as well.

@Name("c10::detail::ivalue_to_const_ref_overload_return<at::Tensor>") @Properties(inherit = org.bytedeco.pytorch.presets.torch.class)
public class ivalue_to_const_ref_overload_return extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public ivalue_to_const_ref_overload_return() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public ivalue_to_const_ref_overload_return(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public ivalue_to_const_ref_overload_return(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public ivalue_to_const_ref_overload_return position(long position) {
        return (ivalue_to_const_ref_overload_return)super.position(position);
    }
    @Override public ivalue_to_const_ref_overload_return getPointer(long i) {
        return new ivalue_to_const_ref_overload_return((Pointer)this).offsetAddress(i);
    }

}
