// Targeted by JavaCPP version 1.5.7-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.depthai;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;
import static org.bytedeco.openblas.global.openblas_nolapack.*;
import static org.bytedeco.openblas.global.openblas.*;
import org.bytedeco.opencv.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_core.*;
import org.bytedeco.opencv.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

import static org.bytedeco.depthai.global.depthai.*;


/**
 * Represents connection between host and device over XLink protocol
 */
@Namespace("dai") @NoOffset @Properties(inherit = org.bytedeco.depthai.presets.depthai.class)
public class XLinkConnection extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public XLinkConnection(Pointer p) { super(p); }

    // static API
    public static native @StdVector DeviceInfo getAllConnectedDevices(@Cast("XLinkDeviceState_t") int state/*=X_LINK_ANY_STATE*/);
    public static native @StdVector DeviceInfo getAllConnectedDevices();
    public static native @ByVal @Cast("std::tuple<bool,dai::DeviceInfo>*") Pointer getFirstDevice(@Cast("XLinkDeviceState_t") int state/*=X_LINK_ANY_STATE*/);
    public static native @ByVal @Cast("std::tuple<bool,dai::DeviceInfo>*") Pointer getFirstDevice();
    public static native @ByVal @Cast("std::tuple<bool,dai::DeviceInfo>*") Pointer getDeviceByMxId(@StdString BytePointer arg0, @Cast("XLinkDeviceState_t") int state/*=X_LINK_ANY_STATE*/);
    public static native @ByVal @Cast("std::tuple<bool,dai::DeviceInfo>*") Pointer getDeviceByMxId(@StdString BytePointer arg0);
    public static native @ByVal @Cast("std::tuple<bool,dai::DeviceInfo>*") Pointer getDeviceByMxId(@StdString String arg0, @Cast("XLinkDeviceState_t") int state/*=X_LINK_ANY_STATE*/);
    public static native @ByVal @Cast("std::tuple<bool,dai::DeviceInfo>*") Pointer getDeviceByMxId(@StdString String arg0);

    public XLinkConnection(@Const @ByRef DeviceInfo deviceDesc, @Cast("std::uint8_t*") @StdVector BytePointer mvcmdBinary, @Cast("XLinkDeviceState_t") int expectedState/*=X_LINK_BOOTED*/) { super((Pointer)null); allocate(deviceDesc, mvcmdBinary, expectedState); }
    private native void allocate(@Const @ByRef DeviceInfo deviceDesc, @Cast("std::uint8_t*") @StdVector BytePointer mvcmdBinary, @Cast("XLinkDeviceState_t") int expectedState/*=X_LINK_BOOTED*/);
    public XLinkConnection(@Const @ByRef DeviceInfo deviceDesc, @Cast("std::uint8_t*") @StdVector BytePointer mvcmdBinary) { super((Pointer)null); allocate(deviceDesc, mvcmdBinary); }
    private native void allocate(@Const @ByRef DeviceInfo deviceDesc, @Cast("std::uint8_t*") @StdVector BytePointer mvcmdBinary);
    public XLinkConnection(@Const @ByRef DeviceInfo deviceDesc, @Cast("std::uint8_t*") @StdVector ByteBuffer mvcmdBinary, @Cast("XLinkDeviceState_t") int expectedState/*=X_LINK_BOOTED*/) { super((Pointer)null); allocate(deviceDesc, mvcmdBinary, expectedState); }
    private native void allocate(@Const @ByRef DeviceInfo deviceDesc, @Cast("std::uint8_t*") @StdVector ByteBuffer mvcmdBinary, @Cast("XLinkDeviceState_t") int expectedState/*=X_LINK_BOOTED*/);
    public XLinkConnection(@Const @ByRef DeviceInfo deviceDesc, @Cast("std::uint8_t*") @StdVector ByteBuffer mvcmdBinary) { super((Pointer)null); allocate(deviceDesc, mvcmdBinary); }
    private native void allocate(@Const @ByRef DeviceInfo deviceDesc, @Cast("std::uint8_t*") @StdVector ByteBuffer mvcmdBinary);
    public XLinkConnection(@Const @ByRef DeviceInfo deviceDesc, @Cast("std::uint8_t*") @StdVector byte[] mvcmdBinary, @Cast("XLinkDeviceState_t") int expectedState/*=X_LINK_BOOTED*/) { super((Pointer)null); allocate(deviceDesc, mvcmdBinary, expectedState); }
    private native void allocate(@Const @ByRef DeviceInfo deviceDesc, @Cast("std::uint8_t*") @StdVector byte[] mvcmdBinary, @Cast("XLinkDeviceState_t") int expectedState/*=X_LINK_BOOTED*/);
    public XLinkConnection(@Const @ByRef DeviceInfo deviceDesc, @Cast("std::uint8_t*") @StdVector byte[] mvcmdBinary) { super((Pointer)null); allocate(deviceDesc, mvcmdBinary); }
    private native void allocate(@Const @ByRef DeviceInfo deviceDesc, @Cast("std::uint8_t*") @StdVector byte[] mvcmdBinary);
    public XLinkConnection(@Const @ByRef DeviceInfo deviceDesc, @StdString String pathToMvcmd, @Cast("XLinkDeviceState_t") int expectedState/*=X_LINK_BOOTED*/) { super((Pointer)null); allocate(deviceDesc, pathToMvcmd, expectedState); }
    private native void allocate(@Const @ByRef DeviceInfo deviceDesc, @StdString String pathToMvcmd, @Cast("XLinkDeviceState_t") int expectedState/*=X_LINK_BOOTED*/);
    public XLinkConnection(@Const @ByRef DeviceInfo deviceDesc, @StdString String pathToMvcmd) { super((Pointer)null); allocate(deviceDesc, pathToMvcmd); }
    private native void allocate(@Const @ByRef DeviceInfo deviceDesc, @StdString String pathToMvcmd);
    public XLinkConnection(@Const @ByRef DeviceInfo deviceDesc, @Cast("XLinkDeviceState_t") int expectedState/*=X_LINK_BOOTED*/) { super((Pointer)null); allocate(deviceDesc, expectedState); }
    private native void allocate(@Const @ByRef DeviceInfo deviceDesc, @Cast("XLinkDeviceState_t") int expectedState/*=X_LINK_BOOTED*/);
    public XLinkConnection(@Const @ByRef DeviceInfo deviceDesc) { super((Pointer)null); allocate(deviceDesc); }
    private native void allocate(@Const @ByRef DeviceInfo deviceDesc);

    public native void setRebootOnDestruction(@Cast("bool") boolean reboot);
    public native @Cast("bool") boolean getRebootOnDestruction();

    public native int getLinkId();

    /**
     * Explicitly closes xlink connection.
     * \note This function does not need to be explicitly called
     * as destructor closes the connection automatically
     */
    public native @Name("close") void _close();

    /**
     * Is the connection already closed (or disconnected)
     */
    public native @Cast("bool") boolean isClosed();
}
