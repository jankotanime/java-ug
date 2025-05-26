import lombok.RequiredArgsConstructor;
import lombok.Getter;

@RequiredArgsConstructor
@Getter
public enum ZadaniePriorytet {
  NISKI(ZadanieN::new),
  SREDNI(ZadanieS::new),
  WYSOKI(ZadanieW::new);

  private final ZadanieConstructor constructor;
}
