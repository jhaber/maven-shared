package org.apache.maven.shared.utils.io;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assume.assumeThat;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;

public class Java7SupportTest
{
    @Test
    public void testIsSymLink()
        throws Exception
    {

        File file = new File( "." );
        if ( Java7Support.isAtLeastJava7() )
        {
            assertFalse( Java7Support.isSymLink( file ) );
        }
    }

    @Test
    public void createAndReadSymlink()
        throws Exception
    {
        assumeThat( System.getProperty( "os.name" ), not( startsWith( "Windows" ) ) );
        File file = new File( "target/fzz" );
        if ( Java7Support.isAtLeastJava7() )
        {
            Java7Support.createSymbolicLink(  file, new File("../target") );

            final File file1 = Java7Support.readSymbolicLink( file );
            assertEquals( "target", file1.getName());
            Java7Support.delete(  file );
        }
    }

}
