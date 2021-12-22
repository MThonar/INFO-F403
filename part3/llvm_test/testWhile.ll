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
%c = alloca i32



br label %whileLoop



whileLoop:
%1 = load i32, i32* %b
%2 = icmp ne i32 %1, 0
br i1 %2, label %innerWhile, label %afterWhile



;1er while
innerWhile:
store i32 %1, i32* %c
br label %secWhile



secWhile:
%3 = load i32, i32* %a
%4 = load i32, i32* %b
%5 = add i32 %3, 1
%6 = icmp sgt i32 %5, %4
br i1 %6, label %innerSecWhile, label %endsecWhile



;deuxieme while
innerSecWhile:
%7 = load i32, i32* %a
%8 = load i32, i32* %b
%9 = sub i32 %7, %8
br label %secWhile



;1er while
endsecWhile:
%10 = load i32, i32* %a
%11 = load i32, i32* %b
%12 = load i32, i32* %c
store i32 %10, i32* %b
store i32 %12, i32* %a
br label %innerWhile



afterWhile:
ret i32 0
}
