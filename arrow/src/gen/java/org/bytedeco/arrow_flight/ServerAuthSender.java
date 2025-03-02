// Targeted by JavaCPP version 1.5.7-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.arrow_flight;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;
import org.bytedeco.arrow.*;
import static org.bytedeco.arrow.global.arrow.*;

import static org.bytedeco.arrow.global.arrow_flight.*;


/** \brief A writer for messages to the client during an
 *  authentication handshake. */
@Namespace("arrow::flight") @Properties(inherit = org.bytedeco.arrow.presets.arrow_flight.class)
public class ServerAuthSender extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public ServerAuthSender(Pointer p) { super(p); }

  public native @ByVal Status Write(@StdString String message);
  public native @ByVal Status Write(@StdString BytePointer message);
}
