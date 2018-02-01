#Postfix Calculator CLI

This is a simple Postfix expression based calculator API. 

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

## Structure

There are two modules (for now).

The "postfix-calculator" contains the core logic. 
Extraneous dependencies were avoided (such as importing 
an entire string utility or popular framework just to use one
method).

The "cli" module is an implementation of a commandline tool where you can try out
the various operations that have been implemented.

In the future, it would be great to add a RESTful service that provides an API
for executing postfix command expressions. With that in mind, and to also allow 
for other methods of communication, I made sure that the core module could be used
in any framework, while designing it in such a way as to be usable in a IoC container 
managed application (i.e. Spring). To verify this, I created the CLI using 
the Spring Boot framework.

If I were to spend more time on this, I would probably want to create a Spring Boot
starter project that would automatically configure the application based on dependencies
and classes that have been included in the classpath. The final application should end up
as a single class with nothing more than the standard SpringBootApplication annotation.

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

