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
      <generate-image-size-report
        reportFile="whatever_you_want.txt"
        checkUpToDate="true"
        failOnUnreadable="false">
          <fileset dir="..." />
            <includes ... />
          </fileset>
      </generate-image-size-report>
    </target>

The fileset is a normal ant fileset that should include all the image files you
want in the report.

The attributes you can set are:

+ _reportFile_ - the name of the file to be generated (required).
+ _checkUpToDate_ - if the report file is newer than all the image files the 
  report won't be regenerated.  Set this to false if you want to force
  regeneration of the report. (Default true).
+ _failOnUnreadable_ - by default, if any files in the fileset cannot be
  interpreted as an image file, the file will be skipped over and not included
  in the report, but the build will not fail.  Set this attribute to true if
  instead you want the build to fail if an unreadable files is found. (Default
  false).

Made in response to [this](http://stackoverflow.com/questions/4093130/ant-task-to-extract-image-dimensions-height-width-from-png-and-jpeg-files).