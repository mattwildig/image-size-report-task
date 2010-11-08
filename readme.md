#Image Size Report Ant Task#

Generates a comma separated text file of filenames ant their dimensions, in the
format:

    <filename1>,<height>,<width>
    <filename2>,<height>,<width>

Build it like this:

    ant jar

Use it like this:

    <taskdef resource='antlib.xml' classpath="path/to/image-size-report.jar" />
    
    ...
    
    <target name="...">
      <generate-image-size-report reportFile="whatever_you_want.txt" checkUpToDate="true">
          <fileset dir="..." />
            <includes ... />
          </fileset>
      </generate-image-size-report>
    </target>

The fileset is a normal ant fileset that should include all the image files you
want in the report.

The attributes you can set are:

+ _reportFile_ - the name of the file to be generated
+ _checkUpToDate_ - if the report file is newer than all the image files the 
  report won't be regenerated.  Set this to false if you want to force
  regeneration of the report (default true).

Made in response to [this](http://stackoverflow.com/questions/4093130/ant-task-to-extract-image-dimensions-height-width-from-png-and-jpeg-files).