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

@NoOffset @Name("c10::optional<c10::Device>") @Properties(inherit = org.bytedeco.pytorch.presets.torch.class)
public class DeviceOptional extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public DeviceOptional(Pointer p) { super(p); }
    public DeviceOptional(Device value) { this(); put(value); }
    public DeviceOptional()       { allocate();  }
    private native void allocate();
    public native @Name("operator =") @ByRef DeviceOptional put(@ByRef DeviceOptional x);


    @Name("value") public native @ByRef Device get();
    @ValueSetter public native DeviceOptional put(@ByRef Device value);
}

