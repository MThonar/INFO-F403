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
br label %whileLoop
whileLoop:
%0 = load i32, i32* %b
%1 = icmp ne i32 %0, 0
br i1 %1, label %innerWhile, label %afterWhile



;1er while
innerWhile:
%c = alloca i32
store i32 %b, i32* %c
br label %secWhile



secWhile
%2 = load i32, i32* %a
%3 = load i32, i32* %b
%4 = add i32 %2, 1
%5 = icmp sgt i32 %4, %3
br i1 %5, label %innerSecWhile, label %endsecWhile



;deuxieme while
innerSecWhile:
%6 = load i32, i32* %a
%7 = load i32, i32* %b
%8 = sub i32 %6, %7
br label %secWhile



;1er while
endsecWhile
store i32 %a, i32* %b
store i32 %c, i32* %a
br label %innerWhile



%afterWhile
ret i32 0
}
