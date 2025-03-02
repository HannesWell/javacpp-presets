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
 // namespace c10
@Name("std::hash<c10::Stream>") @Properties(inherit = org.bytedeco.pytorch.presets.torch.class)
public class StreamHash extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public StreamHash() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public StreamHash(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public StreamHash(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public StreamHash position(long position) {
        return (StreamHash)super.position(position);
    }
    @Override public StreamHash getPointer(long i) {
        return new StreamHash((Pointer)this).offsetAddress(i);
    }

  public native @Cast("std::size_t") @Name("operator ()") @NoException(true) long apply(@ByVal Stream s);
}
