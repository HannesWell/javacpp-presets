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


@Properties(inherit = org.bytedeco.pytorch.presets.torch.class)
public class CopyBytesFunction extends FunctionPointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public    CopyBytesFunction(Pointer p) { super(p); }
    protected CopyBytesFunction() { allocate(); }
    private native void allocate();
    public native void call(
    @Cast("size_t") long nbytes,
    @Const Pointer src,
    @ByVal Device src_device,
    Pointer dst,
    @ByVal Device dst_device);
}
