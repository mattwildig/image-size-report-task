# Image Size Report Ant Task

Generates a report of filenames and image dimensions for a set of image files.
The output file format is customisable, but the default looks like:

    <filename1>,<height1>,<width1>
    <filename2>,<height2>,<width2>

Build it like this (a jar will be generated in a `build` directory):

    ant jar

Use it like this:

    <taskdef resource='antlib.xml' classpath="path/to/image-size-report.jar" />
    
    ...
    
    <target name="...">
      <generate-image-size-report
        reportFile="whatever_you_want.txt"
        checkUpToDate="true"
        failOnUnreadable="false"
        format="{0},{1},{2}">
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
+ _format_ - the format of the output file, as specified by the
  `java.text.MessageFormat` class.  `{0}` is replaced with the path of the
  file (relative to the base directory of the fileset), `{1}` is the height of
  the image and `{2}` is the width. You need to be careful of getting your
  escapes right with this field - both xml escapes (so use `&lt;` rather than
  `<`) and the relevant MessageFormat escapes (mainly single quotes). (Default
  "`{0},{1},{2}`").

Made in response to [this](http://stackoverflow.com/questions/4093130/ant-task-to-extract-image-dimensions-height-width-from-png-and-jpeg-files).