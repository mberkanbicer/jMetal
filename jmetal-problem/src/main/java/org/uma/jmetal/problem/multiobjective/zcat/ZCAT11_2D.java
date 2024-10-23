package org.uma.jmetal.problem.multiobjective.zcat;

/**
 * Problem ZCAT1_2D (configured with 2 objectives), defined in: "Challenging test problems for
 * multi-and many-objective optimization",
 * DOI: https://doi.org/10.1016/j.swevo.2023.101350
 */
public class ZCAT11_2D extends ZCAT11 {
  public ZCAT11_2D() {
    super(2, 30, true, 1, false, false);
  }

  @Override
  public String name() {
    return "ZCAT11_2D";
  }
}
