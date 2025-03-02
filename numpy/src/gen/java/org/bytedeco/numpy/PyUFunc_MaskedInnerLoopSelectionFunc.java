// Targeted by JavaCPP version 1.5.6: DO NOT EDIT THIS FILE

package org.bytedeco.numpy;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;
import static org.bytedeco.openblas.global.openblas_nolapack.*;
import static org.bytedeco.openblas.global.openblas.*;
import org.bytedeco.cpython.*;
import static org.bytedeco.cpython.global.python.*;

import static org.bytedeco.numpy.global.numpy.*;

@Properties(inherit = org.bytedeco.numpy.presets.numpy.class)
public class PyUFunc_MaskedInnerLoopSelectionFunc extends FunctionPointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public    PyUFunc_MaskedInnerLoopSelectionFunc(Pointer p) { super(p); }
    protected PyUFunc_MaskedInnerLoopSelectionFunc() { allocate(); }
    private native void allocate();
    public native int call(
                            PyUFuncObject ufunc,
                            @Cast("PyArray_Descr**") PointerPointer dtypes,
                            PyArray_Descr mask_dtype,
                            @Cast("npy_intp*") SizeTPointer fixed_strides,
                            @Cast("npy_intp") long fixed_mask_stride,
                            @Cast("PyUFunc_MaskedStridedInnerLoopFunc**") PointerPointer out_innerloop,
                            @Cast("NpyAuxData**") PointerPointer out_innerloopdata,
                            IntPointer out_needs_api);
}
