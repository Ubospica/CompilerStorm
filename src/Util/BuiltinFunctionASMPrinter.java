package Util;

import java.io.FileOutputStream;
import java.io.IOException;

public class BuiltinFunctionASMPrinter {
	String builtin_s_as_literal = """
				.text
				.file	"BuiltinFuncC.c"
				.globl	__mx_builtin_malloc     # -- Begin function __mx_builtin_malloc
				.p2align	2
				.type	__mx_builtin_malloc,@function
			__mx_builtin_malloc:                    # @__mx_builtin_malloc
			# %bb.0:
				srai	a1, a0, 31
				tail	malloc
			.Lfunc_end0:
				.size	__mx_builtin_malloc, .Lfunc_end0-__mx_builtin_malloc
			                                        # -- End function
				.globl	__mx_builtin_memset     # -- Begin function __mx_builtin_memset
				.p2align	2
				.type	__mx_builtin_memset,@function
			__mx_builtin_memset:                    # @__mx_builtin_memset
			# %bb.0:
				tail	memset
			.Lfunc_end1:
				.size	__mx_builtin_memset, .Lfunc_end1-__mx_builtin_memset
			                                        # -- End function
				.globl	print                   # -- Begin function print
				.p2align	2
				.type	print,@function
			print:                                  # @print
			# %bb.0:
				lui	a1, %hi(.L.str)
				addi	a1, a1, %lo(.L.str)
				mv	a2, a0
				mv	a0, a1
				mv	a1, a2
				tail	printf
			.Lfunc_end2:
				.size	print, .Lfunc_end2-print
			                                        # -- End function
				.globl	println                 # -- Begin function println
				.p2align	2
				.type	println,@function
			println:                                # @println
			# %bb.0:
				tail	puts
			.Lfunc_end3:
				.size	println, .Lfunc_end3-println
			                                        # -- End function
				.globl	printInt                # -- Begin function printInt
				.p2align	2
				.type	printInt,@function
			printInt:                               # @printInt
			# %bb.0:
				lui	a1, %hi(.L.str.2)
				addi	a1, a1, %lo(.L.str.2)
				mv	a2, a0
				mv	a0, a1
				mv	a1, a2
				tail	printf
			.Lfunc_end4:
				.size	printInt, .Lfunc_end4-printInt
			                                        # -- End function
				.globl	printlnInt              # -- Begin function printlnInt
				.p2align	2
				.type	printlnInt,@function
			printlnInt:                             # @printlnInt
			# %bb.0:
				lui	a1, %hi(.L.str.3)
				addi	a1, a1, %lo(.L.str.3)
				mv	a2, a0
				mv	a0, a1
				mv	a1, a2
				tail	printf
			.Lfunc_end5:
				.size	printlnInt, .Lfunc_end5-printlnInt
			                                        # -- End function
				.globl	getString               # -- Begin function getString
				.p2align	2
				.type	getString,@function
			getString:                              # @getString
				.cfi_startproc
			# %bb.0:
				addi	sp, sp, -16
				.cfi_def_cfa_offset 16
				sw	ra, 12(sp)
				sw	s0, 8(sp)
				.cfi_offset ra, -4
				.cfi_offset s0, -8
				addi	a0, zero, 233
				mv	a1, zero
				call	malloc
				mv	s0, a0
				lui	a0, %hi(.L.str)
				addi	a0, a0, %lo(.L.str)
				mv	a1, s0
				call	__isoc99_scanf
				mv	a0, s0
				lw	s0, 8(sp)
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end6:
				.size	getString, .Lfunc_end6-getString
				.cfi_endproc
			                                        # -- End function
				.globl	getInt                  # -- Begin function getInt
				.p2align	2
				.type	getInt,@function
			getInt:                                 # @getInt
				.cfi_startproc
			# %bb.0:
				addi	sp, sp, -16
				.cfi_def_cfa_offset 16
				sw	ra, 12(sp)
				.cfi_offset ra, -4
				lui	a0, %hi(.L.str.2)
				addi	a0, a0, %lo(.L.str.2)
				addi	a1, sp, 8
				call	__isoc99_scanf
				lw	a0, 8(sp)
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end7:
				.size	getInt, .Lfunc_end7-getInt
				.cfi_endproc
			                                        # -- End function
				.globl	toString                # -- Begin function toString
				.p2align	2
				.type	toString,@function
			toString:                               # @toString
			# %bb.0:
				addi	sp, sp, -16
				sw	ra, 12(sp)
				sw	s0, 8(sp)
				sw	s1, 4(sp)
				mv	s0, a0
				addi	a0, zero, 30
				mv	a1, zero
				call	malloc
				mv	s1, a0
				lui	a0, %hi(.L.str.2)
				addi	a1, a0, %lo(.L.str.2)
				mv	a0, s1
				mv	a2, s0
				call	sprintf
				mv	a0, s1
				lw	s1, 4(sp)
				lw	s0, 8(sp)
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end8:
				.size	toString, .Lfunc_end8-toString
			                                        # -- End function
				.globl	__mx_builtin_strcmp     # -- Begin function __mx_builtin_strcmp
				.p2align	2
				.type	__mx_builtin_strcmp,@function
			__mx_builtin_strcmp:                    # @__mx_builtin_strcmp
			# %bb.0:
				tail	strcmp
			.Lfunc_end9:
				.size	__mx_builtin_strcmp, .Lfunc_end9-__mx_builtin_strcmp
			                                        # -- End function
				.globl	__mx_builtin_strcat     # -- Begin function __mx_builtin_strcat
				.p2align	2
				.type	__mx_builtin_strcat,@function
			__mx_builtin_strcat:                    # @__mx_builtin_strcat
			# %bb.0:
				addi	sp, sp, -32
				sw	ra, 28(sp)
				sw	s0, 24(sp)
				sw	s1, 20(sp)
				sw	s2, 16(sp)
				sw	s3, 12(sp)
				mv	s2, a1
				mv	s3, a0
				call	strlen
				mv	s0, a0
				mv	s1, a1
				mv	a0, s2
				call	strlen
				add	a1, s1, a1
				add	a2, s0, a0
				sltu	a0, a2, s0
				add	a1, a1, a0
				addi	a0, a2, 5
				sltu	a2, a0, a2
				add	a1, a1, a2
				call	malloc
				mv	s0, a0
				mv	a1, s3
				call	strcpy
				mv	a0, s0
				mv	a1, s2
				lw	s3, 12(sp)
				lw	s2, 16(sp)
				lw	s1, 20(sp)
				lw	s0, 24(sp)
				lw	ra, 28(sp)
				addi	sp, sp, 32
				tail	strcat
			.Lfunc_end10:
				.size	__mx_builtin_strcat, .Lfunc_end10-__mx_builtin_strcat
			                                        # -- End function
				.globl	__mx_str_length         # -- Begin function __mx_str_length
				.p2align	2
				.type	__mx_str_length,@function
			__mx_str_length:                        # @__mx_str_length
			# %bb.0:
				addi	sp, sp, -16
				sw	ra, 12(sp)
				call	strlen
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end11:
				.size	__mx_str_length, .Lfunc_end11-__mx_str_length
			                                        # -- End function
				.globl	__mx_str_substring      # -- Begin function __mx_str_substring
				.p2align	2
				.type	__mx_str_substring,@function
			__mx_str_substring:                     # @__mx_str_substring
			# %bb.0:
				addi	sp, sp, -32
				sw	ra, 28(sp)
				sw	s0, 24(sp)
				sw	s1, 20(sp)
				sw	s2, 16(sp)
				sw	s3, 12(sp)
				mv	s3, a1
				mv	s2, a0
				sub	s1, a2, a1
				addi	a0, s1, 1
				srai	a1, a0, 31
				call	malloc
				mv	s0, a0
				add	a1, s2, s3
				mv	a2, s1
				call	memcpy
				add	a0, s0, s1
				sb	zero, 0(a0)
				mv	a0, s0
				lw	s3, 12(sp)
				lw	s2, 16(sp)
				lw	s1, 20(sp)
				lw	s0, 24(sp)
				lw	ra, 28(sp)
				addi	sp, sp, 32
				ret
			.Lfunc_end12:
				.size	__mx_str_substring, .Lfunc_end12-__mx_str_substring
			                                        # -- End function
				.globl	__mx_str_parseInt       # -- Begin function __mx_str_parseInt
				.p2align	2
				.type	__mx_str_parseInt,@function
			__mx_str_parseInt:                      # @__mx_str_parseInt
				.cfi_startproc
			# %bb.0:
				addi	sp, sp, -16
				.cfi_def_cfa_offset 16
				sw	ra, 12(sp)
				.cfi_offset ra, -4
				lui	a1, %hi(.L.str.2)
				addi	a1, a1, %lo(.L.str.2)
				addi	a2, sp, 8
				call	__isoc99_sscanf
				lw	a0, 8(sp)
				lw	ra, 12(sp)
				addi	sp, sp, 16
				ret
			.Lfunc_end13:
				.size	__mx_str_parseInt, .Lfunc_end13-__mx_str_parseInt
				.cfi_endproc
			                                        # -- End function
				.globl	__mx_str_ord            # -- Begin function __mx_str_ord
				.p2align	2
				.type	__mx_str_ord,@function
			__mx_str_ord:                           # @__mx_str_ord
			# %bb.0:
				add	a0, a0, a1
				lb	a0, 0(a0)
				ret
			.Lfunc_end14:
				.size	__mx_str_ord, .Lfunc_end14-__mx_str_ord
			                                        # -- End function
				.type	.L.str,@object          # @.str
				.section	.rodata.str1.1,"aMS",@progbits,1
			.L.str:
				.asciz	"%s"
				.size	.L.str, 3

				.type	.L.str.2,@object        # @.str.2
			.L.str.2:
				.asciz	"%d"
				.size	.L.str.2, 3

				.type	.L.str.3,@object        # @.str.3
			.L.str.3:
				.asciz	"%d\\n"
				.size	.L.str.3, 4

				.ident	"clang version 10.0.0-4ubuntu1 "
				.section	".note.GNU-stack","",@progbits
				.addrsig
			""";
	public BuiltinFunctionASMPrinter(String dst) throws IOException {
		FileOutputStream out = new FileOutputStream(dst);
		out.write(builtin_s_as_literal.getBytes());
	}
}