// Targeted by JavaCPP version 1.5.7-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.arrow;

import org.bytedeco.arrow.Function;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;

import static org.bytedeco.arrow.global.arrow.*;


/** \brief FileSystem, path pair */
@Namespace("arrow::fs") @Properties(inherit = org.bytedeco.arrow.presets.arrow.class)
public class FileLocator extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public FileLocator() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public FileLocator(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public FileLocator(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public FileLocator position(long position) {
        return (FileLocator)super.position(position);
    }
    @Override public FileLocator getPointer(long i) {
        return new FileLocator((Pointer)this).offsetAddress(i);
    }

  public native @SharedPtr FileSystem filesystem(); public native FileLocator filesystem(FileSystem setter);
  public native @StdString String path(); public native FileLocator path(String setter);
}
