/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.zeppelin.integration;

import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.zeppelin.interpreter.InterpreterException;
import org.apache.zeppelin.interpreter.InterpreterSetting;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RunWith(value = Parameterized.class)
public class SparkIntegrationTest24 extends SparkIntegrationTest {

  public SparkIntegrationTest24(String sparkVersion, String hadoopVersion) {
    super(sparkVersion, hadoopVersion);
  }

  @Parameterized.Parameters
  public static List<Object[]> data() {
    return Arrays.asList(new Object[][]{
            {"2.4.8", "2.7"}
    });
  }

  @Override
  public void testYarnClusterMode() throws IOException, YarnException, InterruptedException, InterpreterException, XmlPullParserException {
    InterpreterSetting sparkInterpreterSetting = interpreterSettingManager.getInterpreterSettingByName("spark");
    sparkInterpreterSetting.setProperty("spark.sql.execution.arrow.sparkr.enabled", "false");
    super.testYarnClusterMode();
  }
}
