/*******************************************************************************
 * Copyright (c) 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.jaxrs20.cdi12.fat.contextandCDI;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.enterprise.context.Dependent;

@Dependent
@ApplicationPath("contextandCDI22")
public class CDIApplication22 extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        LinkedHashSet<Class<?>> classes = new LinkedHashSet<>();
        classes.add(CDIFilter.class);
        return classes;
        
    }
    
    @Override
    public Set<Object> getSingletons() {
       
        LinkedHashSet<Object> classes = new LinkedHashSet<>();
        TestResource resource = getBean(TestResource.class);
//        TestResource resource = new TestResource();
        classes.add(resource);
        return classes;
    }
    
    public <E> E getBean(Class<E> clazz, Annotation... qualifiers) {

        Instance<E> instance = CDI.current().select(clazz, qualifiers);

        if (instance.isUnsatisfied()) {
            throw new RuntimeException();
        }
        if (instance.isAmbiguous()) {
            throw new RuntimeException();
        }

        return instance.get();
    }
}

