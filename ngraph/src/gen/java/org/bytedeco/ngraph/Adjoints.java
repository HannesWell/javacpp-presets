// Targeted by JavaCPP version 1.5.6: DO NOT EDIT THIS FILE

package org.bytedeco.ngraph;

import org.bytedeco.ngraph.Allocator;
import org.bytedeco.ngraph.Function;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.ngraph.global.ngraph.*;

        @Namespace("ngraph::autodiff") @NoOffset @Properties(inherit = org.bytedeco.ngraph.presets.ngraph.class)
public class Adjoints extends Pointer {
            static { Loader.load(); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Adjoints(Pointer p) { super(p); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Adjoints(long size) { super((Pointer)null); allocateArray(size); }
            private native void allocateArray(long size);
            @Override public Adjoints position(long position) {
                return (Adjoints)super.position(position);
            }
            @Override public Adjoints getPointer(long i) {
                return new Adjoints((Pointer)this).offsetAddress(i);
            }
        
            /** \brief (dy/dx)(c) for all x used to compute y
             * 
             *  @param y The dependent value
             *  @param c An expression for where to evaluate the derivatives */
            public Adjoints(@Cast("const ngraph::OutputVector*") @ByRef NodeOutputVector y, @Cast("const ngraph::OutputVector*") @ByRef NodeOutputVector c) { super((Pointer)null); allocate(y, c); }
            private native void allocate(@Cast("const ngraph::OutputVector*") @ByRef NodeOutputVector y, @Cast("const ngraph::OutputVector*") @ByRef NodeOutputVector c);

            public Adjoints(@Const @ByRef Adjoints adjoints) { super((Pointer)null); allocate(adjoints); }
            private native void allocate(@Const @ByRef Adjoints adjoints);
            public native @ByRef @Name("operator =") Adjoints put(@Const @ByRef Adjoints adjoints);
            
            ///
            public Adjoints() { super((Pointer)null); allocate(); }
            private native void allocate();

            /** \brief (dy/dx)(c)
             * 
             *  @param x The node whose adjoint is desired. */
            
            ///
            public native @Cast("const ngraph::OutputVector*") @ByRef NodeOutputVector get(@Const @ByRef NodeOutput x);

            /** \brief Add a backprop contribution to x's adjoint
             * 
             *  @param x The adjoint node
             *  @param delta A backprop contribution */
            
            ///
            public native void add_delta(@Const @ByRef NodeOutput x,
                                       @Const @ByRef NodeOutput delta,
                                       @Cast("size_t") long output_index/*=0*/);
            public native void add_delta(@Const @ByRef NodeOutput x,
                                       @Const @ByRef NodeOutput delta);

            /** \brief Add a backprop contribution to a slice of x's adjoint
             * 
             *  @param x The adjoint node
             *  @param delta A backprop contribution
             *  @param lower_bounds Lower bounds of slice to add to
             *  @param upper_bounds Upper bounds of slice to add to
             *  @param strides Strides of slice to add to */
            public native void add_delta_to_slice(@Const @ByRef NodeOutput x,
                                                @Const @ByRef NodeOutput delta,
                                                @Const @ByRef Coordinate lower_bounds,
                                                @Const @ByRef Coordinate upper_bounds,
                                                @Const @ByRef Strides strides);

            public native @SharedPtr @ByVal Node backprop_node(@Const @ByRef NodeOutput x);
            public native @ByVal NodeOutput backprop_output(@Const @ByRef NodeOutput x);
        }
