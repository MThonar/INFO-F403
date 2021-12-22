@.strR = private unnamed_addr constant [3 x i8] c"%d\00", align 1

define i32 @readInt() {
  %1 = alloca i32, align 4
  %2 = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.strR, i32 0, i32 0), i32* %1)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}

declare i32 @scanf(i8*, ...)

@.strP = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

define void @println(i32 %x) {
  %1 = alloca i32, align 4
  store i32 %x, i32* %1, align 4
  %2 = load i32, i32* %1, align 4
  %3 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %2)
  ret void
}

declare i32 @printf(i8*, ...)

define i32 @main() {
%a = alloca i32
store i32 3, i32* %a
%b = alloca i32
store i32 2, i32* %b
br label %whileLoop

whileLoop:
%1 = load i32, i32* %b
%2 = icmp ne i32 %1, 0
br i1 %2, label %innerWhile, label %afterWhile

;1er while
innerWhile:
%c = alloca i32
store i32 %1, i32* %c
%3 = load i32, i32* %c
call void @println(i32 %3) ;doit print: 1
br label %secWhile

secWhile:
%4 = load i32, i32* %a
%5 = load i32, i32* %b
%6 = add i32 %4, 1
%7 = icmp sgt i32 %6, %5
br i1 %7, label %innerSecWhile, label %endsecWhile

;deuxieme while
innerSecWhile:
%8 = load i32, i32* %a
%9 = load i32, i32* %b
%10 = sub i32 %8, %9
call void @println(i32 %10) ;ou %8
call void @println(i32 %9) ;ou %9
br label %secWhile

;1er while
endsecWhile:
%11 = load i32, i32* %a
%12 = load i32, i32* %b
%13 = load i32, i32* %c
store i32 %11, i32* %b
store i32 %13, i32* %a
br label %innerWhile

afterWhile:
ret i32 0
}
