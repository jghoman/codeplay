       IDENTIFICATION DIVISION.
       PROGRAM-ID. HELLO-WORLD.
      * simple hello world program
      *
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       77 RESULT PIC 99.

       PROCEDURE DIVISION.
           ADD 9 3 GIVING RESULT.
           DISPLAY RESULT.
       DISPLAY 'Hello world, how are we all doing?'.
       STOP RUN.
