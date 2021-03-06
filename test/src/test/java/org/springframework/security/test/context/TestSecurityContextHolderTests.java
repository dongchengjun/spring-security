/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.test.context;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class TestSecurityContextHolderTests {

    private SecurityContext context;

    @Before
    public void setup() {
        context = SecurityContextHolder.createEmptyContext();
    }

    @After
    public void cleanup() {
        TestSecurityContextHolder.clearContext();
    }

    @Test
    public void clearContextClearsBoth() {
        SecurityContextHolder.setContext(context);
        TestSecurityContextHolder.setContext(context);

        TestSecurityContextHolder.clearContext();

        assertThat(SecurityContextHolder.getContext()).isNotSameAs(context);
        assertThat(TestSecurityContextHolder.getContext()).isNotSameAs(context);
    }

    @Test
    public void getContextDefaultsNonNull() {
        assertThat(TestSecurityContextHolder.getContext()).isNotNull();
        assertThat(SecurityContextHolder.getContext()).isNotNull();
    }

    @Test
    public void setContextSetsBoth() {
        TestSecurityContextHolder.setContext(context);

        assertThat(TestSecurityContextHolder.getContext()).isSameAs(context);
        assertThat(SecurityContextHolder.getContext()).isSameAs(context);
    }
}