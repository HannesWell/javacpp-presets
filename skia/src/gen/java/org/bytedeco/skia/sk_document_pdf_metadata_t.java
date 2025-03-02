// Targeted by JavaCPP version 1.5.6: DO NOT EDIT THIS FILE

package org.bytedeco.skia;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;

import static org.bytedeco.skia.global.Skia.*;


@Properties(inherit = org.bytedeco.skia.presets.Skia.class)
public class sk_document_pdf_metadata_t extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public sk_document_pdf_metadata_t() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public sk_document_pdf_metadata_t(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public sk_document_pdf_metadata_t(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public sk_document_pdf_metadata_t position(long position) {
        return (sk_document_pdf_metadata_t)super.position(position);
    }
    @Override public sk_document_pdf_metadata_t getPointer(long i) {
        return new sk_document_pdf_metadata_t((Pointer)this).offsetAddress(i);
    }

    public native sk_string_t fTitle(); public native sk_document_pdf_metadata_t fTitle(sk_string_t setter);
    public native sk_string_t fAuthor(); public native sk_document_pdf_metadata_t fAuthor(sk_string_t setter);
    public native sk_string_t fSubject(); public native sk_document_pdf_metadata_t fSubject(sk_string_t setter);
    public native sk_string_t fKeywords(); public native sk_document_pdf_metadata_t fKeywords(sk_string_t setter);
    public native sk_string_t fCreator(); public native sk_document_pdf_metadata_t fCreator(sk_string_t setter);
    public native sk_string_t fProducer(); public native sk_document_pdf_metadata_t fProducer(sk_string_t setter);
    public native sk_time_datetime_t fCreation(); public native sk_document_pdf_metadata_t fCreation(sk_time_datetime_t setter);
    public native sk_time_datetime_t fModified(); public native sk_document_pdf_metadata_t fModified(sk_time_datetime_t setter);
    public native float fRasterDPI(); public native sk_document_pdf_metadata_t fRasterDPI(float setter);
    public native @Cast("bool") boolean fPDFA(); public native sk_document_pdf_metadata_t fPDFA(boolean setter);
    public native int fEncodingQuality(); public native sk_document_pdf_metadata_t fEncodingQuality(int setter);
}
