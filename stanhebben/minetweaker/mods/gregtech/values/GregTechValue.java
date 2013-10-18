/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class GregTechValue extends TweakerValue {
	public static final GregTechValue INSTANCE = new GregTechValue();
	
	private GregTechValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case FUSIONREACTOR:
				return FusionReactorValue.INSTANCE;
			case CENTRIFUGE:
				return CentrifugeValue.INSTANCE;
			case ELECTROLYZER:
				return ElectrolyzerValue.INSTANCE;
			case CHEMICAL:
				return ChemicalValue.INSTANCE;
			case CANNER:
				return CannerValue.INSTANCE;
			case BLASTFURNACE:
				return BlastFurnaceValue.INSTANCE;
			case ALLOYSMELTER:
				return AlloySmelterValue.INSTANCE;
			case ASSEMBLER:
				return AssemblerValue.INSTANCE;
			case WIREMILL:
				return WiremillValue.INSTANCE;
			case PLATEBENDER:
				return PlateBenderValue.INSTANCE;
			case IMPLOSIONCOMPRESSOR:
				return ImplosionCompressorValue.INSTANCE;
			case GRINDER:
				return GrinderValue.INSTANCE;
			case DISTILLATIONTOWER:
				return DistillationTowerValue.INSTANCE;
			case LATHE:
				return LatheValue.INSTANCE;
			//#ifndef MC152
			case CUTTER:
				return CutterValue.INSTANCE;
			//#endif
			case VACUUMFREEZER:
				return VacuumFreezerValue.INSTANCE;
			case SAWMILL:
				return SawmillValue.INSTANCE;
			case DIESELGENERATOR:
				return GeneratorValue.INSTANCE_DIESEL;
			case GASTURBINE:
				return GeneratorValue.INSTANCE_GASTURBINE;
			case THERMALGENERATOR:
				return GeneratorValue.INSTANCE_THERMAL;
			case DENSEFLUIDGENERATOR:
				return GeneratorValue.INSTANCE_DENSEFLUID;
			case PLASMAGENERATOR:
				return GeneratorValue.INSTANCE_PLASMA;
			case MAGICGENERATOR:
				return GeneratorValue.INSTANCE_MAGIC;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "gregtech";
	}
}
