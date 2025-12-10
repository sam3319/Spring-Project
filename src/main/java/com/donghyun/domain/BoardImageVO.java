package com.donghyun.domain;

import lombok.Data;

/**
 * Description : BoardImageVO<br>
 * Date : 2025. 12. 5.<br>
 * History :<br>
 * - 작성자 : 이동현, 날짜 : 2025. 12. 5.<br>
 *
 * @author 이동현
 * @version 1.0
 */
@Data
public class BoardImageVO {

	private Long imageId, bno;
	private String fileName;
}
