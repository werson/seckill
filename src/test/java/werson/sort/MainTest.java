package werson.sort;

import org.junit.Test;
import org.seckill.entity.Seckill;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @version 1.0.0
 * @since 2018/5/29
 */
public class MainTest {

	@Test
	public void ByteTest(){
		BigDecimal a = BigDecimal.valueOf(1L);
		System.out.println(a.subtract(null));
	}


	@Test
	public void testSorted(){
		List<Seckill> seckillList = new ArrayList<>();
		Seckill seckill = new Seckill();
		seckill.setName("gagaga");
		seckill.setSeckillId(1);
		seckillList.add(seckill);
		Seckill seckill2 = new Seckill();
		seckill2.setName("wawa");
		seckill2.setSeckillId(2);
		seckillList.add(seckill2);
		List<Seckill> seckillList2 = seckillList.stream().sorted(Comparator.comparing(Seckill::getSeckillId).reversed()).collect(Collectors.toList());
		for (Seckill s : seckillList2) {
			System.out.println(s.getName());
		}
	}

}
