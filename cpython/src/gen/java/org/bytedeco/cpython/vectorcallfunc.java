// Targeted by JavaCPP version 1.5.6: DO NOT EDIT THIS FILE

package org.bytedeco.cpython;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;

import static org.bytedeco.cpython.global.python.*;


public class vectorcallfunc extends FunctionPointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public    vectorcallfunc(Pointer p) { super(p); }
    protected vectorcallfunc() { allocate(); }
    private native void allocate();
    public native PyObject call(PyObject callable, @Cast("PyObject *const *") PointerPointer args,
                                    @Cast("size_t") long nargsf, PyObject kwnames);
}
