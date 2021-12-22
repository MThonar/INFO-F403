def i32 simpleIf(i32 %a, i32 %b){
%cond = icmp slt i32 %a, %b
br i1 %cond, label %lower, label %greaterORequals
lower:
call void i32 @println(i32 %a)
ret i32 0
greaterORequals:
call void i32 @println(i32 %b)
ret i32 0
}

def i32 simpleFor(i32 %a, i32 %b, i32 %c){
i = alloca i32
store i32 %a, i32* %i ; a=1
br label %forLoop
forLoop:
%1 = load i32, i32* %i
%2 = icmp slt i32 %1, %c ; c=3
br i1 %2, label %innerFor, label %endFor
%3 = call i32 @println(i32 2) ;on print a dans la fonction not_in_euclid.co. je mets 2 pour combler
br label %forLoop
endFor:
ret i32 0
}

define i32 @main() {
%a = alloca i32
store i32 5, i32* %a
%1 = call i32 @simpleIf(i32 %a, i32 %b)
%2 = call i32 @simpleFor(i32 %a, i32 %b, i32 %c)
return i32 0
}