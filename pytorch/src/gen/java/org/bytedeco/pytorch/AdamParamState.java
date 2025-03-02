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


@Namespace("torch::optim") @NoOffset @Properties(inherit = org.bytedeco.pytorch.presets.torch.class)
public class AdamParamState extends OptimizerCloneableAdamParamState {
    static { Loader.load(); }
    /** Default native constructor. */
    public AdamParamState() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public AdamParamState(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public AdamParamState(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public AdamParamState position(long position) {
        return (AdamParamState)super.position(position);
    }
    @Override public AdamParamState getPointer(long i) {
        return new AdamParamState((Pointer)this).offsetAddress(i);
    }

  public native @Cast("int64_t*") @ByRef @NoException(true) LongPointer step();
  public native @ByRef @NoException(true) Tensor exp_avg();
  public native @ByRef @NoException(true) Tensor exp_avg_sq();
  public native @ByRef @NoException(true) Tensor max_exp_avg_sq();
  
  
  
  // NOLINTNEXTLINE(modernize-use-override)
}
