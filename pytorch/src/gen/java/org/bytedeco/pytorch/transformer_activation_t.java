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

@NoOffset @Name("c10::variant<torch::enumtype::kReLU,torch::enumtype::kGELU>") @Properties(inherit = org.bytedeco.pytorch.presets.torch.class)
public class transformer_activation_t extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public transformer_activation_t(Pointer p) { super(p); }
    public transformer_activation_t(kReLU value) { this(); put(value); }
    public transformer_activation_t(kGELU value) { this(); put(value); }
    public transformer_activation_t()       { allocate();  }
    private native void allocate();
    public native @Name("operator =") @ByRef transformer_activation_t put(@ByRef transformer_activation_t x);

    public @ByRef kReLU get0() { return get0(this); }
    @Namespace @Name("c10::get<0>") public static native @ByRef kReLU get0(@ByRef transformer_activation_t container);
    @ValueSetter public native transformer_activation_t put(@ByRef kReLU value);
    public @ByRef kGELU get1() { return get1(this); }
    @Namespace @Name("c10::get<1>") public static native @ByRef kGELU get1(@ByRef transformer_activation_t container);
    @ValueSetter public native transformer_activation_t put(@ByRef kGELU value);
}

