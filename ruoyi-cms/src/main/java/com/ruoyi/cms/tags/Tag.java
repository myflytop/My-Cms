package com.ruoyi.cms.tags;

import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

/**
 * 自定义Tag标签
 * @author bobey
 *
 */
public class Tag extends AbstractProcessorDialect {
	private static final String DIALECT_NAME = "Phxl Dialect";//定义方言名称
	protected Tag(String name, String prefix, int processorPrecedence) {
		 // 我们将设置此方言与“方言处理器”优先级相同
        // 标准方言, 以便处理器执行交错。
        super(DIALECT_NAME, "phxl", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		// TODO Auto-generated method stub
		return null;
	}

}
