package com.beamlytics.examples.transforms;

import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;


public class NewTransform2 extends PTransform< PCollection<String>,PCollection<KV<String,Integer>> >{

    @Override
    public @UnknownKeyFor @NonNull @Initialized PCollection<KV<String,Integer>> expand(
            @UnknownKeyFor @NonNull @Initialized PCollection<String> input) {
        

                return input.apply("NewTransform2",MapElements
                .into(TypeDescriptors.kvs(TypeDescriptors.strings(), TypeDescriptors.integers()
                )).via((String s) -> KV.of(s, s.length()) ));
    }
}