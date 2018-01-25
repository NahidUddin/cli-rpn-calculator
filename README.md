# Calculator CLI #

This is a basic command line tool that implements 
(currently) four basic operators. Order of operations
is not yet implemented, so at this point at most 
only 3 tokens can be entered and only in order 
(ie, two numbers separated by an operand).

###### To Run ######


Linux: 

From the shell, execute the "run" script, using the current directory qualifier.


    $ ./run

Also make sure that permissions are correct, and that the "run" file is executable.

    $ chmod +x run


Windows: 

Execute the "run.cmd" from a command prompt.

    > run
    
Requires Java 8 to run.

#### Possible operators: 
* ADDITION: +
* SUBTRACTION: -
* MULTIPLICATION: * 
* DIVISION: /

#### Examples:
  
*Basic Syntax*

    > 1 + 12
*Decimals Allowed*

    > 1.1 - 10.1 
        
*Results Rounded*

    > 1.01 + 1.0
    2 

*Trailing '0' Decimal removed* 
    
    > 1.0 + 1.0
    2
     
*Previous Values Persist*
 
    > - 1
    1
     
*Spaces are required*
 
    > -1-1 

*Simple number entry stores the first number*
 
    > 1 2
    1
         
*Trailing operators are ignored*
 
        > 1 1 +
        1
        
