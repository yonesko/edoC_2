package gleb.devices;

import gleb.Util;

public class CeilingFan {
    String location;
    Speed speed;

    public CeilingFan(String location) {
        this.location = location;
        speed = Speed.OFF;
    }

    public void setSpeed(Speed speed) {
        Util.log(speed.toString());
        this.speed = speed;
    }

    public Speed getSpeed() {
        return speed;
    }

    public enum Speed {
        OFF,
        LOW,
        MEDIUM,
        HIGH;

        public Speed prev() {
            int i = this.ordinal();
            Speed[] values = Speed.values();

            if (i == 0)
                return this;

            return values[i - 1];
        }

        public Speed next() {
            int i = this.ordinal();
            Speed[] values = Speed.values();

            if (i == values.length - 1)
                return this;

            return values[i + 1];
        }

    }
}