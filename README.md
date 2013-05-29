# financeParser03

Was going to build this into something more when I get a chance. The basic 
project is a simple fetcher to the sec.gov website to fetch XBRL files from
their ftp site. It works in 2 passes.

1. The parser fetches the key files that tells you where on the ftp site
the zip files are located. The first program fetches and builds a list
of stuff to download.

2. The fetcher program takes the results of the first part and grabs the
actual zip files that contain the XBRL files.



Lots of things wrong with it.
- No param file to configure timeouts.
- No jar file. I just left it in source form since I was using it in eclipse.


# plan going forward when I get some time.

Was going put the XBRL data into some DB and run some analysis. I got stuck
a bit with how XBRL works but sort of got that figured out. Life kicked in
and I haven't really had time on this.
