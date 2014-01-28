/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.transformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;
import static stanhebben.minetweaker.transformer.MineTweakerASM.*;

/**
 *
 * @author Stanneke
 */
public class EntityLivingBaseTransformer implements IMineTweakerTransformer {
	private final boolean obfuscated;
	
	public EntityLivingBaseTransformer(boolean obfuscated) {
		this.obfuscated = obfuscated;
	}
	
	@Override
	public byte[] transform(byte[] bytecode) {
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES) {
			@Override
			protected String getCommonSuperClass(final String type1, final String type2) {
				//System.out.println("getCommonSuperClass " + type1 + " & " + type2);
				if ((type1.equals(obfuscated ? CLASS_ENTITYPLAYER_O : CLASS_ENTITYPLAYER_N) && type2.equals(obfuscated ? CLASS_ENTITYLIVINGBASE_O : CLASS_ENTITYLIVINGBASE_N))
						|| (type2.equals(obfuscated ? CLASS_ENTITYPLAYER_O : CLASS_ENTITYPLAYER_N) && type1.equals(obfuscated ? CLASS_ENTITYLIVINGBASE_O : CLASS_ENTITYLIVINGBASE_N))) {
					return obfuscated ? CLASS_ENTITYLIVINGBASE_O : CLASS_ENTITYLIVINGBASE_N;
				}
				return "java/lang/Object";
			}
		};
		ClassReader reader = new ClassReader(bytecode);
		reader.accept(new MyClassVisitor(writer), ClassReader.EXPAND_FRAMES);
		return writer.toByteArray();
	}
	
	private class MyClassVisitor extends ClassVisitor {
		MyClassVisitor(ClassVisitor writer) {
			super(ASM4, writer);
		}
		
		@Override
		public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
			//System.out.println("Visiting method " + name + " -- " + desc);
			if (obfuscated
					&& name.equals(MineTweakerASM.METHOD_ENTITYLIVINGBASE_APPLYARMORCALCULATIONS_O)
					&& desc.equals(METHOD_ENTITYLIVINGBASE_APPLYARMORCALCULATIONS_SIG_O)) {
				return new Injector(super.visitMethod(access, name, desc, signature, exceptions));
			} else if (!obfuscated
					&& name.equals(MineTweakerASM.METHOD_ENTITYLIVINGBASE_APPLYARMORCALCULATIONS_N)
					&& desc.equals(METHOD_ENTITYLIVINGBASE_APPLYARMORCALCULATIONS_SIG_N)) {
				return new Injector(super.visitMethod(access, name, desc, signature, exceptions));
			} else {
				return super.visitMethod(access, name, desc, signature, exceptions);
			}
		}
	}
	
	private class Injector extends MethodVisitor {
		public Injector(MethodVisitor original) {
			super(ASM4, original);
		}
		
		@Override
		public void visitCode() {
			super.visitCode();
			
			System.out.println("Injecting damage modifier in EntityLivingBase");
			
			String cnDamageSource = obfuscated ? CLASS_DAMAGESOURCE_O : CLASS_DAMAGESOURCE_N;
			String cnEntityLivingBase = obfuscated ? CLASS_ENTITYLIVINGBASE_O : CLASS_ENTITYLIVINGBASE_N;
			
			//DamageModifier modifier = DamageTweaker.getModifier(par1DamageSource);
			//if (modifier != null) {
			//	return modifier.applyArmorDamage(this, par1DamageSource, par2);
			//}
			
			if (MineTweakerASM.debugMode) {
				visitIntInsn(ALOAD, 0);
				visitIntInsn(ALOAD, 1);
				visitIntInsn(FLOAD, 2);
				visitMethodInsn(INVOKESTATIC, "stanhebben/minetweaker/damage/DamageTweaker", "debugApplyArmorCalculations", "(L" + cnEntityLivingBase + ";L" + cnDamageSource + ";F)V");
			}
			
			Label skip = new Label();
			
			visitIntInsn(ALOAD, 1);
			visitMethodInsn(INVOKESTATIC, "stanhebben/minetweaker/damage/DamageTweaker", "getModifier", "(L" + cnDamageSource + ";)Lstanhebben/minetweaker/damage/DamageModifier;");
			visitInsn(DUP);
			visitInsn(ACONST_NULL);
			visitJumpInsn(IF_ACMPEQ, skip);
			visitIntInsn(ALOAD, 0);
			visitIntInsn(ALOAD, 1);
			visitIntInsn(FLOAD, 2);
			visitMethodInsn(INVOKEVIRTUAL, "stanhebben/minetweaker/damage/DamageModifier", "applyArmorDamage", "(L" + cnEntityLivingBase + ";L" + cnDamageSource + ";F)F");
			visitInsn(FRETURN);
			
			visitLabel(skip);
			visitInsn(POP);
		}
	}
}
