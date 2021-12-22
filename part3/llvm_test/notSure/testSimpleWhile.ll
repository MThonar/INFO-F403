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

define i32* @assign1(i32 %x) {
	%a = alloca i32
	store i32 %x, i32* %a
	ret i32* %a
}

define i32* @assign2(i32* %x) {
	%a = alloca i32
	%1 = load i32, i32* %x
	store i32 %1, i32* %a
	ret i32* %a
}

define i32 @plus(i32 %x, i32 %y) {
	%plus = alloca i32
	%intermediate1 = alloca i32
	store i32 %x, i32* %intermediate1
	%1 = load i32, i32* %intermediate1
	%intermediate2 = alloca i32
	store i32 %y, i32* %intermediate2
	%2 = load i32, i32* %intermediate2
	%3 = add i32 %1,%2
	ret i32 %3
}

define void @whileInferiorPlus(i32* %a, i32* %x, i32 %y){
br label %whileLoop
whileLoop:
%1 = load i32, i32* %a
%2 = load i32, i32* %x
%3 = icmp slt i32 %1, %2
br i1 %3, label %endWhile, label %afterWhile
endWhile:
%4 = load i32, i32* %a
%5 = call i32 @plus(i32 %4, i32 %y)
store i32 %5, i32* %a
call void @println(i32 %5)
br label %whileLoop
afterWhile:
ret void
}

define i32 @main() {
%a = call i32* @assign1(i32 5)
%b = call i32* @assign1(i32 10)
call void @whileInferiorPlus(i32* %a, i32* %b, i32 1)
ret i32 0
}
