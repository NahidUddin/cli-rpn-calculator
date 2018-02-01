#Postfix Calculator CLI

To run the Postfix Calculator CLI from the command prompt, 
simply enter "run" in the root directory of the project.
Requires Java 8 to run.

A postfix expression is an expression in which the operands are followed by the operator  
unlike expressions in the familiar format, where the operator is in between the operands. 
For example, this standard "familiar" expression ... 
    
    6 * (5 - 2 + 4) 

... can be written in postfix form like this: 

    6 5 2 - 4 + *
     
Not every mathematical operator is implemented in this version at the moment.
 
 ## To Run
 
 Requires Java 8
 
 #### On Linux 
 
 From the shell, execute the "run" script, using the current directory qualifier.
 
 
     $ ./run
 
 Also make sure that permissions are correct, and that the "run" file is executable.
 
     $ chmod +x run
 
 
 #### On Windows 
 
 Execute the "run.cmd" from a command prompt.
 
     > run


## Possible operators

    ADDITION: +
    SUBTRACTION: -
    MULTIPLICATION: *
    DIVISION: /

## Examples:

#### Basic Syntax

    > 1 1 +
    2 +
    
#### Decimals Allowed

    > 1.1 1     
    0.1
    
#### Results Rounded

    > 1.01 1.0 +
    2 
    
#### Trailing '0' Decimal removed

    > 1.0 1.0 + 
    2 
    
#### Previous Values Persist

    > 1.0 1.0 + 
    2
    > 3 -
    -1 
    
#### Spaces are required

    > -1
    -1
    > 2+22*
    >Invalid Token
    
#### Operator only is allowed, and will calculate the last two numbers entered 

    > 1 1 +
    1 +
    > + 
    2 +
    
#### Simple number entry will show the last number entered

    > 1 2 3
    3

