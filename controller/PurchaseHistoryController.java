package jp.co.internous.samurai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.samurai.model.domain.dto.PurchaseHistoryDto;
import jp.co.internous.samurai.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.samurai.model.session.LoginSession;

/**
 * 購入履歴情報に関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/samurai/history")
public class PurchaseHistoryController {

	/*
	 * フィールド定義
	 */
	@Autowired
	private TblPurchaseHistoryMapper purchaseHistoryMapper;
	@Autowired
	private LoginSession loginSession;
	
	/**
	 * 購入履歴画面を初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return 購入履歴画面
	 */
	@RequestMapping("/")
	public String index(Model m) {
		
		List<PurchaseHistoryDto> history = purchaseHistoryMapper.findByUserId(loginSession.getUserId());
		m.addAttribute("historyList", history);
		m.addAttribute("loginSession", loginSession);
		
		return "purchase_history";
		
	}
	
	/**
	 * 購入履歴情報を論理削除する
	 * @return true:削除成功、false:削除失敗
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete() {
		int deletedCount =purchaseHistoryMapper.logicalDeleteByUserId(loginSession.getUserId());
		
		return deletedCount > 0;
	}
}
