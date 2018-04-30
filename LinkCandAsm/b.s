.global add
.type add,@function
add:
	addl %edi,%esi;
	movl %esi,%eax;
	ret;