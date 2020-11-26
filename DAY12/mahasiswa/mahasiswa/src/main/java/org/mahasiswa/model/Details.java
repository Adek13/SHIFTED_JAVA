package org.mahasiswa.model;


abstract class Details {

    public int physics, calculus, biologi;

    public abstract int getPhysics();

    public abstract void setPhysics(int physics);

    public abstract int getCalculus();

    public abstract void setCalculus(int calculus);

    public abstract int getBiologi();

    public abstract void setBiologi(int biologi);

}
