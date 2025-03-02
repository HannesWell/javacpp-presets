// Targeted by JavaCPP version 1.5.6: DO NOT EDIT THIS FILE

package org.bytedeco.liquidfun;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;

import static org.bytedeco.liquidfun.global.liquidfun.*;


@Properties(inherit = org.bytedeco.liquidfun.presets.liquidfun.class)
public class b2DynamicTreeQueryCallback extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public b2DynamicTreeQueryCallback() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public b2DynamicTreeQueryCallback(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public b2DynamicTreeQueryCallback(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public b2DynamicTreeQueryCallback position(long position) {
        return (b2DynamicTreeQueryCallback)super.position(position);
    }
    @Override public b2DynamicTreeQueryCallback getPointer(long i) {
        return new b2DynamicTreeQueryCallback((Pointer)this).offsetAddress(i);
    }

  @Virtual(true) public native @Cast("bool") boolean QueryCallback(@Cast("int32") int nodeId);
}
