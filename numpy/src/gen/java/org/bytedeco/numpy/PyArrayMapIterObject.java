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



/*
 * Store the information needed for fancy-indexing over an array. The
 * fields are slightly unordered to keep consec, dataptr and subspace
 * where they were originally.
 */
@Properties(inherit = org.bytedeco.numpy.presets.numpy.class)
public class PyArrayMapIterObject extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public PyArrayMapIterObject() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public PyArrayMapIterObject(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public PyArrayMapIterObject(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public PyArrayMapIterObject position(long position) {
        return (PyArrayMapIterObject)super.position(position);
    }
    @Override public PyArrayMapIterObject getPointer(long i) {
        return new PyArrayMapIterObject((Pointer)this).offsetAddress(i);
    }

        public native @ByRef PyObject ob_base(); public native PyArrayMapIterObject ob_base(PyObject setter);
        /*
         * Multi-iterator portion --- needs to be present in this
         * order to work with PyArray_Broadcast
         */

        public native int numiter(); public native PyArrayMapIterObject numiter(int setter);                 /* number of index-array
                                                          iterators */
        public native @Cast("npy_intp") long size(); public native PyArrayMapIterObject size(long setter);                    /* size of broadcasted
                                                          result */
        public native @Cast("npy_intp") long index(); public native PyArrayMapIterObject index(long setter);                   /* current index */
        public native int nd(); public native PyArrayMapIterObject nd(int setter);                      /* number of dims */
        public native @Cast("npy_intp") long dimensions(int i); public native PyArrayMapIterObject dimensions(int i, long setter);
        @MemberGetter public native @Cast("npy_intp*") SizeTPointer dimensions(); /* dimensions */
        public native NpyIter outer(); public native PyArrayMapIterObject outer(NpyIter setter);                  /* index objects
                                                          iterator */
        public native Pointer unused(int i); public native PyArrayMapIterObject unused(int i, Pointer setter);
        @MemberGetter public native @Cast("void**") PointerPointer unused();
        public native PyArrayObject array(); public native PyArrayMapIterObject array(PyArrayObject setter);
        /* Flat iterator for the indexed array. For compatibility solely. */
        public native PyArrayIterObject ait(); public native PyArrayMapIterObject ait(PyArrayIterObject setter);

        /*
         * Subspace array. For binary compatibility (was an iterator,
         * but only the check for NULL should be used).
         */
        public native PyArrayObject subspace(); public native PyArrayMapIterObject subspace(PyArrayObject setter);

        /*
         * if subspace iteration, then this is the array of axes in
         * the underlying array represented by the index objects
         */
        public native int iteraxes(int i); public native PyArrayMapIterObject iteraxes(int i, int setter);
        @MemberGetter public native IntPointer iteraxes();
        public native @Cast("npy_intp") long fancy_strides(int i); public native PyArrayMapIterObject fancy_strides(int i, long setter);
        @MemberGetter public native @Cast("npy_intp*") SizeTPointer fancy_strides();

        /* pointer when all fancy indices are 0 */
        public native @Cast("char*") BytePointer baseoffset(); public native PyArrayMapIterObject baseoffset(BytePointer setter);

        /*
         * after binding consec denotes at which axis the fancy axes
         * are inserted.
         */
        public native int consec(); public native PyArrayMapIterObject consec(int setter);
        public native @Cast("char*") BytePointer dataptr(); public native PyArrayMapIterObject dataptr(BytePointer setter);

        public native int nd_fancy(); public native PyArrayMapIterObject nd_fancy(int setter);
        public native @Cast("npy_intp") long fancy_dims(int i); public native PyArrayMapIterObject fancy_dims(int i, long setter);
        @MemberGetter public native @Cast("npy_intp*") SizeTPointer fancy_dims();

        /* Whether the iterator (any of the iterators) requires API */
        public native int needs_api(); public native PyArrayMapIterObject needs_api(int setter);

        /*
         * Extra op information.
         */
        public native PyArrayObject extra_op(); public native PyArrayMapIterObject extra_op(PyArrayObject setter);
        public native PyArray_Descr extra_op_dtype(); public native PyArrayMapIterObject extra_op_dtype(PyArray_Descr setter);         /* desired dtype */
        public native @Cast("npy_uint32*") IntPointer extra_op_flags(); public native PyArrayMapIterObject extra_op_flags(IntPointer setter);         /* Iterator flags */

        public native NpyIter extra_op_iter(); public native PyArrayMapIterObject extra_op_iter(NpyIter setter);
        public native NpyIter_IterNextFunc extra_op_next(); public native PyArrayMapIterObject extra_op_next(NpyIter_IterNextFunc setter);
        public native @Cast("char*") BytePointer extra_op_ptrs(int i); public native PyArrayMapIterObject extra_op_ptrs(int i, BytePointer setter);
        public native @Cast("char**") PointerPointer extra_op_ptrs(); public native PyArrayMapIterObject extra_op_ptrs(PointerPointer setter);

        /*
         * Information about the iteration state.
         */
        public native NpyIter_IterNextFunc outer_next(); public native PyArrayMapIterObject outer_next(NpyIter_IterNextFunc setter);
        public native @Cast("char*") BytePointer outer_ptrs(int i); public native PyArrayMapIterObject outer_ptrs(int i, BytePointer setter);
        public native @Cast("char**") PointerPointer outer_ptrs(); public native PyArrayMapIterObject outer_ptrs(PointerPointer setter);
        public native @Cast("npy_intp*") SizeTPointer outer_strides(); public native PyArrayMapIterObject outer_strides(SizeTPointer setter);

        /*
         * Information about the subspace iterator.
         */
        public native NpyIter subspace_iter(); public native PyArrayMapIterObject subspace_iter(NpyIter setter);
        public native NpyIter_IterNextFunc subspace_next(); public native PyArrayMapIterObject subspace_next(NpyIter_IterNextFunc setter);
        public native @Cast("char*") BytePointer subspace_ptrs(int i); public native PyArrayMapIterObject subspace_ptrs(int i, BytePointer setter);
        public native @Cast("char**") PointerPointer subspace_ptrs(); public native PyArrayMapIterObject subspace_ptrs(PointerPointer setter);
        public native @Cast("npy_intp*") SizeTPointer subspace_strides(); public native PyArrayMapIterObject subspace_strides(SizeTPointer setter);

        /* Count for the external loop (which ever it is) for API iteration */
        public native @Cast("npy_intp") long iter_count(); public native PyArrayMapIterObject iter_count(long setter);

}
