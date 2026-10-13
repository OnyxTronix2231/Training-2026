package frc.robot.lib;

import com.ctre.phoenix6.BaseStatusSignal;
import frc.robot.lib.OnyxMotorOutputs.CanBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignalManager {

    private static final List<BaseStatusSignal> allSignalsRio = new ArrayList<>();
    private static final List<BaseStatusSignal> allSignalsCanivore = new ArrayList<>();

    /** The lists above as arrays, built once so refreshAll() doesn't create new arrays each cycle. */
    private static BaseStatusSignal[] rioArray = new BaseStatusSignal[0];
    private static BaseStatusSignal[] canivoreArray = new BaseStatusSignal[0];

    /** True when a signal was added and the arrays need rebuilding. */
    private static boolean dirty = true;

    public static void register(CanBus canBus, BaseStatusSignal... signals) {
        if (canBus == CanBus.RIO) {
            allSignalsRio.addAll(Arrays.asList(signals));
        } else {
            allSignalsCanivore.addAll(Arrays.asList(signals));
        }
        dirty = true;
    }

    /** Rebuilds the arrays. Also called automatically by {@link #refreshAll()} when needed. */
    public static void freeze() {
        canivoreArray = allSignalsCanivore.toArray(new BaseStatusSignal[0]);
        rioArray = allSignalsRio.toArray(new BaseStatusSignal[0]);
        dirty = false;
    }

    /** Refreshes all signals. Call once per loop, before any subsystem periodic() runs. */
    public static void refreshAll() {
        if (dirty) {
            freeze();
        }
        if (canivoreArray.length > 0) {
            BaseStatusSignal.refreshAll(canivoreArray);
        }
        if (rioArray.length > 0) {
            BaseStatusSignal.refreshAll(rioArray);
        }
    }
}
