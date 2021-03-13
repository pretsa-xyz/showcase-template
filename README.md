# PrimeFaces Showcase Template

JSF template based on the awesome [PrimeFaces Showcase](https://github.com/primefaces/primefaces-showcase).


# Features

 - Responsive layout based on [PrimeFlex](https://github.com/primefaces/primeflex)
 - Theme switcher

# Getting started

#### 1. First include it in your classpath, it's not available on Maven Central yet so add JitPack repository first:
```
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```
```
<dependency>
    <groupId>xyz.pretsa</groupId>
    <artifactId>showcase-template</artifactId>
    <version>10.0.0-SNAPSHOT</version>
</dependency>
```
It will bring the following transitive dependencies:
```
<dependency>
    <groupId>org.primefaces</groupId>
    <artifactId>primefaces</artifactId>
    <version>10.0.0-RC1</version>
</dependency>
```
#### 2. Create a template based on showcase-template template
```src\main\webapp\WEB-INF\template.xhtml```
```html
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
                xmlns:h="http://java.sun.com/jsf/html"
                xmlns:f="http://java.sun.com/jsf/core"
                xmlns:ui="http://java.sun.com/jsf/facelets"
                xmlns:p="http://primefaces.org/ui"
                template="/template.xhtml">
    <ui:define name="title">App Title</ui:define>

    <ui:define name="top-menu">
        <ul class="topbar-menu">
            <li>
                <h:link outcome="/index">Home</h:link>
            </li>
            <li class="topbar-submenu">
                <a tabindex="0">About</a>
                <ul class="connected-overlay-in">
                    <li class="topbar-submenu-header">About</li>
                    <li><h:link outcome="/index">Company</h:link></li>
                    <li class="topbar-submenu-header">Contact us</li>
                    <li><h:link outcome="/index">Request a call</h:link></li>
                    <li><h:link outcome="/index">Address</h:link></li>
                </ul>
            </li>
        </ul>
    </ui:define>

    <ui:define name="footer-left">
        <span>PrimeFaces Showcase Template 10.0.0 by </span>
        <a href="https://pretsa.xyz">PRETSA.XYZ</a>
    </ui:define>

    <ui:define name="footer-right">
        <a href="https://github.com/primefaces/primefaces" class="p-mr-3"><i class="pi pi-github"></i></a>
        <a href="https://twitter.com/primefaces" class="p-mr-3"><i class="pi pi-twitter"></i></a>
        <a href="https://discord.com/invite/gzKFYnpmCY"><i class="pi pi-discord"></i></a>
    </ui:define>

</ui:composition>
```
#### 3. Create a page based on your just created template
```src\main\webapp\index.xhtml```
```html
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
                xmlns:h="http://java.sun.com/jsf/html"
                xmlns:f="http://java.sun.com/jsf/core"
                xmlns:ui="http://java.sun.com/jsf/facelets"
                xmlns:p="http://primefaces.org/ui"
                template="WEB-INF/template.xhtml">

    <ui:define name="content">
        <div class="ui-fluid p-p-6">
            <div class="p-field p-grid">
                <label for="firstname" class="p-col-12 p-mb-2 p-md-2 p-mb-md-0">Firstname</label>
                <div class="p-col-12 p-md-10">
                    <p:inputText id="firstname" type="text" />
                </div>
            </div>
            <div class="p-field p-grid">
                <label for="lastname" class="p-col-12 p-mb-2 p-md-2 p-mb-md-0">Lastname</label>
                <div class="p-col-12 p-md-10">
                    <p:inputText id="lastname" type="text" />
                </div>
            </div>
            <div class="p-field p-grid">
                <label for="dob" class="p-col-12 p-mb-2 p-md-2 p-mb-md-0">Date of Birth</label>
                <div class="p-col-12 p-md-10">
                    <p:datePicker id="dob"  showButtonBar="true" showTime="true" timeZone="GMT+2" showIcon="true"/>
                </div>
            </div>
        </div>
    </ui:define>
</ui:composition>
```
#### 4. Create side menu bean by creating a class annotated with ```@Specializes``` and extending ```org.primefaces.showcase.menu.AppMenu```
```java
@ApplicationScoped
@Specializes
public class MyAppMenu extends AppMenu {

    @PostConstruct
    public void init() {
        menuCategories = new ArrayList<>();
        menuItems = new ArrayList<>();

        List<MenuItem> myPages = new ArrayList<>();
        myPages.add(new MenuItem("Home", "/index"));
        myPages.add(new MenuItem("Products", "/index", "X"));
        menuCategories.add(new MenuCategory("My Pages", myPages));

        for (MenuCategory category : menuCategories) {
            for (MenuItem menuItem : category.getMenuItems()) {
                menuItem.setParentLabel(category.getLabel());
                if (menuItem.getUrl() != null) {
                    menuItems.add(menuItem);
                }
                if (menuItem.getMenuItems() != null) {
                    for (MenuItem item : menuItem.getMenuItems()) {
                        item.setParentLabel(menuItem.getLabel());
                        if (item.getUrl() != null) {
                            menuItems.add(item);
                        }
                    }
                }
            }
        }
    }
}
```
#### 5. Accessing selected theme (optional)
Selected theme, input style & mode can by accessed by injecting ```org.primefaces.showcase.view.app.App``` instance
```java
@Inject App app;
```
```java
app.getTheme()
app.getInputStyle()
app.isDarkMode()
```
#### 6. Build & Run your project

# Template sections
#### Favicon
- Name ```favicon```
- Sample value
```html
<ui:define name="favicon">
<link href="#{resource['showcase/images/favicon-32x32.png']}" rel="icon" type="image/png" sizes="32x32"/>
<link href="#{resource['showcase/images/favicon-16x16.png']}" rel="icon" type="image/png" sizes="16x16"/>
</ui:define>
```

#### Page title
- Name ````title````
- Sample value
````html
<ui:define name="title">App Title</ui:define>
````

#### HTML Head
The template already includes ```head``` tag, you can add to it using this section
- Name ```head```
- Sample value
```html
<ui:define name="head">
<meta name="viewport" content="width=device-width">
</ui:define>
```
#### Logo
- Name ```logo```
- Sample value
```html
<ui:define name="logo">
<p:graphicImage name="images/primefaces-logo.svg" library="showcase" />
</ui:define>
```

#### Topbar menu
- Name ```top-menu```
- Sample value
```html
<ui:define name="top-menu">
<ul class="topbar-menu">
	<li><h:link outcome="/index">Home</h:link></li>
	<li class="topbar-submenu">
		<a tabindex="0">Themes</a>
		<ul class="connected-overlay-in">
			<li class="topbar-submenu-header">PRIMEONE Themes</li>
			<li><p:commandLink actionListener="#{app.changeTheme('saga', false)}" oncomplete="App.changeTheme('saga', false)" update=":app-theme-logo"><h:graphicImage name="showcase/images/themes/saga.png" alt="Saga"/><span>Saga</span></p:commandLink></li>
			<li><p:commandLink actionListener="#{app.changeTheme('vela', true)}" oncomplete="App.changeTheme('vela', true)" update=":app-theme-logo"><h:graphicImage name="showcase/images/themes/vela.png" alt="Vela"/><span>Vela</span></p:commandLink></li>
			<li><p:commandLink actionListener="#{app.changeTheme('arya', true)}" oncomplete="App.changeTheme('arya', true)" update=":app-theme-logo"><h:graphicImage name="showcase/images/themes/arya.png" alt="Arya"/><span>Arya</span></p:commandLink></li>
		</ul>
	</li>
</ul>
</ui:define>
```

#### Side menu
Side menu can be populated programaticly
- Name ```side-menu```
- Sample class ```MyAppMenu.java```
```java
@ApplicationScoped
@Specializes
public class MyAppMenu extends AppMenu {

    @PostConstruct
    public void init() {
        menuCategories = new ArrayList<>();

        List<MenuItem> myPages = new ArrayList<>();
        myPages.add(new MenuItem("Home", "/index"));
        myPages.add(new MenuItem("Products", "/index", "New"));
        menuCategories.add(new MenuCategory("My Pages", myPages));

    }

}
```
   - Sample value
```html
<ui:define name="side-menu">
<ui:repeat value="#{appMenu.menuCategories}" var="menuCategory">
	<div class="menu-category">#{menuCategory.label}</div>
	<div class="menu-items">
		<ui:repeat value="#{menuCategory.menuItems}" var="menuItem">
			<h:outputLink rendered="#{empty menuItem.url and not empty menuItem.badge}" styleClass="hidden">
				#{menuItem.label}<p:tag value="#{menuItem.badge}" severity="warning" styleClass="p-ml-2" rendered="#{not empty menuItem.badge}"/>
			</h:outputLink>
			<h:outputLink value="#{menuItem.url}" rendered="#{not empty menuItem.url and empty menuItem.badge and not menuItem.url.startsWith('/') and empty menuItem.menuItems}">
				#{menuItem.label}
			</h:outputLink>
			<h:link outcome="#{menuItem.url}" rendered="#{menuItem.url.startsWith('/') and empty menuItem.menuItems}">
				#{menuItem.label}<p:tag value="#{menuItem.badge}" styleClass="p-ml-2" rendered="#{not empty menuItem.badge}"/>
			</h:link>
			<p:outputPanel rendered="#{not empty menuItem.menuItems}">
				<a id="sl_#{menuItem.label.toLowerCase()}" tabindex="0" class="submenu-link">
					#{menuItem.label}
				</a>
				<div class="submenu">
					<ul>
						<ui:repeat value="#{menuItem.menuItems}" var="nestedMenuItem">
							<li><h:link outcome="#{nestedMenuItem.url}">
									#{nestedMenuItem.label}
									<p:tag value="#{menuItem.badge}" styleClass="p-ml-2" rendered="#{not empty menuItem.badge}"/>
								</h:link></li>
						</ui:repeat>
					</ul>
				</div>
			</p:outputPanel>
		</ui:repeat>
	</div>
</ui:repeat>
</ui:define>
```
#### Content area (page)
- Name ```content```
- Sample value
```html
<ui:define name="content">
<div class="ui-fluid p-p-6">
	<div class="p-field p-grid">
		<label for="firstname" class="p-col-12 p-mb-2 p-md-2 p-mb-md-0">Firstname</label>
		<div class="p-col-12 p-md-10">
			<p:inputText id="firstname" type="text" />
		</div>
	</div>
	<div class="p-field p-grid">
		<label for="lastname" class="p-col-12 p-mb-2 p-md-2 p-mb-md-0">Lastname</label>
		<div class="p-col-12 p-md-10">
			<p:inputText id="lastname" type="text" />
		</div>
	</div>
	<div class="p-field p-grid">
		<label for="dob" class="p-col-12 p-mb-2 p-md-2 p-mb-md-0">Date of Birth</label>
		<div class="p-col-12 p-md-10">
			<p:datePicker id="dob"  showButtonBar="true" showTime="true" timeZone="GMT+2" showIcon="true"/>
		</div>
	</div>
</div>
</ui:define>
```

#### Footer (left)
- Name ```footer-left```
- Sample value
```html
<ui:define name="footer-left">
<span>PrimeFaces 10.0.0 by </span>
<a href="https://www.primetek.com.tr">PrimeTek</a>
</ui:define>
```

#### Footer (right)
- Name ```footer-right```
- Sample value
```html
<ui:define name="footer-right">
<a href="https://github.com/primefaces/primefaces" class="p-mr-3"><i class="pi pi-github"></i></a>
<a href="https://twitter.com/primefaces" class="p-mr-3"><i class="pi pi-twitter"></i></a>
<a href="https://discord.com/invite/gzKFYnpmCY"><i class="pi pi-discord"></i></a>
</ui:define>
```

#### Ajax status indicator
- Name ```status```
- Sample value
```html
<ui:define name="status">
<p:ajaxStatus styleClass="status-indicator">
	<f:facet name="start">
		<i class="pi pi-spin pi-spinner" aria-hidden="true"></i>
	</f:facet>

	<f:facet name="complete">
		<h:outputText value=""/>
	</f:facet>
</p:ajaxStatus>
</ui:define>
```

#### HTML Body
The template already includes ```body``` tag, you can add to it using this section
- Name ```body```
- Sample value
```html
<div>any additional tags</div>
```

