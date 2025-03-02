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
public class PyArray_FastTakeFunc extends FunctionPointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public    PyArray_FastTakeFunc(Pointer p) { super(p); }
    protected PyArray_FastTakeFunc() { allocate(); }
    private native void allocate();
    public native int call(Pointer dest, Pointer src, @Cast("npy_intp*") SizeTPointer indarray,
                                       @Cast("npy_intp") long nindarray, @Cast("npy_intp") long n_outer,
                                       @Cast("npy_intp") long m_middle, @Cast("npy_intp") long nelem,
                                       @Cast("NPY_CLIPMODE") int clipmode);
}
