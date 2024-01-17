# About
Elite Global is a powerful and user-friendly corporate banking accounts aggregator designed to give businesses complete control and visibility over their finances. It goes beyond basic aggregation, offering comprehensive account management, powerful analytics, and intuitive customization to streamline financial operations and optimize decision-making.

<a href="https://showcase.onwavemaker.com/EliteGlobal/" target="_blank">Click Here</a> for more details on Elite Global app

# Personas
## Corporate Manager
Executives, treasurers, finance managers, or any authorized personnel responsible for managing the company's finances.

### Responsibilities:
- Monitor account balances and transactions
- Initiate payments and transfers
- Analyze financial trends
- Customize dashboards, and leverage insights for informed decision-making

# Prefabs
## Corporate Summary
One-stop dashboard for all bank accounts, categorized by type, with drill-down for details and trend analysis.
### UI Properties
- **corporateid**: Corporate ID

## Rapid Pay
Secure instant transfers, bulk payments, and multi-level approval workflows for seamless corporate transactions.
### UI Properties
- **corporateid**: Corporate ID

## Credit Line chart
Interactive visualization of credit line utilization across loans, helping optimize borrowing strategies.

## Transaction Calendar
Unified calendar for upcoming and past transactions, with filtering, search, and reminder functionality for organized cash flow management.
### UI Properties
- **corporateid**: Corporate ID

# Setup
## Database Setup
- Provide your database connection details from the database tab in WaveMaker studio or replace the connection placeholders in `profiles` folder and `db-connection-setting.json`
- The Sample DB dump can be found in the root folder

# Information about Project Folder Structure and Files

## pom.xml
Add any maven dependencies to this file. Dependencies declared in this file will be available on the classpath.

## lib
Add custom jar files to this folder. Files added to this folder will be copied to WEB-INF/lib/ on the classpath.

## services
All services should be added via studio. Once added, services can be edited via eclipse or other editors, including adding additional classes.
Classes in this folder will be compiled when the project is run or deployed.
Files added to this folder will be copied to WEB-INF/classes/ on the classpath.
Modifications to imported services can be lost upon re-import.

To see external updates in studio, use the refresh button in the java editor.

## src/main/java
Add your application sources such as java class files to this folder.
Files added to this folder will be copied to WEB-INF/classes/ on the classpath.

## src/main/resources
Add your application resources such as properties and xml files to this folder.
Files added to this folder will be copied to WEB-INF/classes/ on the classpath.

## src/main/webapp
Add web application sources to this folder.
Files you need to know:
- **app.css:** Application CSS
- **index.html:** Can be edited directly to customize, such as including meta, script and other tags.
- **app.js:** Contains any application owned component definitions and functions.
- **app.variables.json:** Contains any application variable definition.

## src/test/java
Add your unit tests specific to the application such as JUnit tests to this folder.

## src/test/resources
Add your test resources such as properties and xml files to this folder.

## src/main/webapp/WEB-INF/data
This data directory is for HSQLDB Databases.

By default, it contains some sample databases.
If your project does not use these sample database, you can delete these files and directory to reduce the size of your project.

You can also add your own HSQLDB database or other data files to this directory. All HSQLDB databases must be in this directory.

## src/main/webapp/pages
Each project page creates a folder by the name of the page, i.e Main.
All page files in the pages folder are studio managed.
Files you need to know:
- **Page CSS (i.e. Main.css):** Contains custom css added in source, css or by applying custom styles to components.
- **Page HTML (i.e. Main.html):** Contains any custom markup added in the source, markup editor. Can be edited with the project closed.
- **Page JS (i.e. Main.js):** Can be edited via the file system. Use the refresh button in the source, script panel if you edit this file outside of studio.
- **Page Variable (i.e. Main.variable.json):** Can be edited via the file system. Use the refresh button in the source, script panel if you edit this file outside of studio.

## src/main/webapp/services
Contains service definition files used by studio. These files are not user editable.

## src/main/webapp/resources
Created upon first use of the resources panel in studio. These are the folders uses by the resources panel and resources in binding.

## src/main/webapp/WEB-INF
web.xml is the Web Application Deployment Descriptor in which you can add custom servlets,filters and listeners.

## src/main/webapp/WEB-INF/classes
This folder is populated by studio. Do not edit or add any files to this folder. Changes will be lost. Use src/main/resources instead.

## src/main/webapp/WEB-INF/lib
This folder is populated by studio. Do not edit or add any files to this folder. Changes will be lost. Add jars into lib directory of the project or add dependencies in the pom.xml instead.
## Build and Deploy
The application contains set of Docker Files which can be used to build and deploy the application. Refer the below link for more information.
https://docs.wavemaker.com/learn/app-development/deployment/build-with-docker
