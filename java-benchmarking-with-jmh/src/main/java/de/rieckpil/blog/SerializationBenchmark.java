/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package de.rieckpil.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SerializationBenchmark {

  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final Gson gson = new Gson();

  @Benchmark
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @Warmup(iterations = 5)
  @Measurement(iterations = 10)
  @BenchmarkMode(Mode.AverageTime)
  public User benchmarkSerializationWithJackson(SerializationDataProvider serializationDataProvider) throws IOException {
    return objectMapper.readValue(serializationDataProvider.jsonString, User.class);
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @Warmup(iterations = 5)
  @Measurement(iterations = 10)
  @BenchmarkMode(Mode.AverageTime)
  public User benchmarkSerializationWithGSON(SerializationDataProvider serializationDataProvider) {
    return gson.fromJson(serializationDataProvider.jsonString, User.class);
  }

  @State(Scope.Benchmark)
  public static class SerializationDataProvider {

    private String jsonString;

    @Setup(Level.Invocation)
    public void setup() {
      this.jsonString = "{\"firstName\": \"Mike\", \"lastName\":\"Duke\", \"hobbies\": [{\"name\": \"Soccer\", " +
        "\"tags\": [\"Teamsport\", \"Ball\", \"Outdoor\", \"Championship\"]}], \"address\":" +
        " { \"street\": \"Mainstreet\", \"streetNumber\": \"1A\", \"city\": \"New York\", \"country\":\"USA\", " +
        "\"postalCode\": 1337}}";
    }
  }
}
