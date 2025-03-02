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

@NoOffset @Name("c10::optional<std::string>") @Properties(inherit = org.bytedeco.pytorch.presets.torch.class)
public class StringOptional extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public StringOptional(Pointer p) { super(p); }
    public StringOptional(BytePointer value) { this(); put(value); }
    public StringOptional(String value) { this(); put(value); }
    public StringOptional()       { allocate();  }
    private native void allocate();
    public native @Name("operator =") @ByRef StringOptional put(@ByRef StringOptional x);


    @Name("value") public native @StdString BytePointer get();
    @ValueSetter public native StringOptional put(@StdString BytePointer value);
    @ValueSetter public native StringOptional put(@StdString String value);
}

