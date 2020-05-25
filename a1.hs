type Nibble = (Bool, Bool, Bool, Bool)
type Byte = (Nibble, Nibble)

shiftByte :: Byte -> Byte
shiftByte ((a, b, c, d), (e, f, g, h)) = ((b, c, d, e), (f, g, h, False))

rippleCarryAdder :: Byte -> Byte -> Byte
rippleCarryAdder ((a7, a6, a5, a4), (a3, a2, a1, a0)) ((b7, b6, b5, b4), (b3, b2, b1, b0)) = 
  let fa0 = fulladder False a0 b0
      fa1 = fulladder (snd fa0) a1 b1
      fa2 = fulladder (snd fa1) a2 b2
      fa3 = fulladder (snd fa2) a3 b3
      fa4 = fulladder (snd fa3) a4 b4
      fa1 = fulladder (snd fa4) a5 b5
      fa2 = fulladder (snd fa5) a6 b6
      fa3 = fulladder (snd fa6) a7 b7 in
      (((fst fa7), (fst fa6), (fst fa5), (fst fa4)), 
      ((fst fa3), (fst fa2), (fst fa1), (fst fa0)))

fulladder :: Bool -> Bool -> Bool -> (Bool, Bool)
fulladder a b c
  | (a, b, c) == (False, False, False) = (False, False)
  | (a, b, c) == (False, False, True) = (False, True)
  | (a, b, c) == (False, True, False) = (False, True)
  | (a, b, c) == (False, True, True) = (True, False)
  | (a, b, c) == (True, False, False) = (False, True)
  | (a, b, c) == (True, False, True) = (True, False)
  | (a, b, c) == (True, True, False) = (True, False)
  | (a, b, c) == (True, True, True) = (True, True)

