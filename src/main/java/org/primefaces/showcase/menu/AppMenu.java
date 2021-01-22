/*
 * The MIT License
 *
 * Copyright (c) 2009-2021 PrimeTek
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.primefaces.showcase.menu;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class AppMenu {

    List<MenuCategory> menuCategories;

    @PostConstruct
    public void init() {
        menuCategories = new ArrayList<>();

        //GENERAL CATEGORY START
        List<MenuItem> generalMenuItems = new ArrayList<>();
        generalMenuItems.add(new MenuItem("Content Security", "https://primefaces.github.io/primefaces/10_0_0/#/core/contentsecuritypolicy"));
        generalMenuItems.add(new MenuItem("Forum", "https://forum.primefaces.org"));
        generalMenuItems.add(new MenuItem("Discord Chat", "https://discord.gg/gzKFYnpmCY"));
        generalMenuItems.add(new MenuItem("Source Code", "https://github.com/primefaces/primefaces"));
        generalMenuItems.add(new MenuItem("Store", "https://www.primefaces.org/store"));
        menuCategories.add(new MenuCategory("General", generalMenuItems));
        //GENERAL CATEGORY END

        //THEMING CATEGORY START
        List<MenuItem> myPages = new ArrayList<>();
        myPages.add(new MenuItem("Home", "/index"));
        myPages.add(new MenuItem("Home", "/index", "5"));
        menuCategories.add(new MenuCategory("My Pages", myPages));
        //THEMING CATEGORY END

    }

    public List<MenuCategory> getMenuCategories() {
        return menuCategories;
    }
}
